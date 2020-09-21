package com.jeffrey.manager;

import com.google.common.collect.Lists;
import com.jeffrey.context.constant.UserConstant;
import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.BaseException;
import com.jeffrey.context.properties.SmsProperties;
import com.jeffrey.dto.response.ResponseDTO;
import com.jeffrey.utils.HttpUtil;
import com.jeffrey.utils.json.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Description: 短信服务
 *
 * @author WQ
 * @date 2020/8/21 9:57 AM
 */
@Slf4j
@Component
public class SmsManager {

    @Autowired
    private SmsProperties smsProperties;

    @Autowired
    private Environment environment;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 生成验证码
     *
     * @param length
     * @return
     */
    public static String createVerifyCode(int length) {
        String verifyCode = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            verifyCode = verifyCode + Math.abs(random.nextInt()) % 10;
        }
        return verifyCode;
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    public ResponseDTO sendVerifyCode(String phone) {
        String verifyCodePrefix = UserConstant.VERIFY_CODE_MANAGER_LOGIN_PREFIX;
        String tplId = smsProperties.getVerifyTemplateId();
        long smsCacheTimeout = smsProperties.getVerifyCodeEffectiveTime() * 60;

        if (Lists.newArrayList("local", "dev", "staging2").contains(environment.getProperty("spring.profiles.active"))) {
            cacheManager.set(cacheManager.getKey(String.format(verifyCodePrefix, phone)), UserConstant.VERIFY_CODE_DEV_MOCK, smsCacheTimeout);
        } else {
            String verifyCode = createVerifyCode(6);
            String key = cacheManager.getKey(String.format(verifyCodePrefix, phone));
            cacheManager.set(key, verifyCode, smsCacheTimeout);
            sendVerifyCodeByTpl(phone, verifyCode, tplId);
            log.info(String.format("短信发送缓存的 key=%s,值=%s", key, verifyCode));
        }
        return ResponseDTO.success();
    }

    public void checkVerifyCode(String cacheKey, String inputVerifyCode) {
        cacheKey = cacheManager.getKey(cacheKey);
        checkCode(cacheKey, inputVerifyCode);
        // 从缓存中移除
        cacheManager.del(cacheKey);
    }

    private void checkCode(String cacheKey, String inputVerifyCode) {
        String sVerifyCode = "";
        Object redisCode = cacheManager.get(cacheKey);
        if (redisCode != null) {
            sVerifyCode = (String) redisCode;
        }
        log.info(String.format("短信验证缓存的 key=%s，缓存值=%s，收到的值=%s", cacheKey, sVerifyCode, inputVerifyCode));
        if (!sVerifyCode.equals(inputVerifyCode)) {
            throw new BaseException(ErrorCodeEnum._10010);
        }
    }

    /**
     * 按指定模板发送短信验证码
     *
     * @param phone
     * @param verifyCode
     */
    public void sendVerifyCodeByTpl(String phone, String verifyCode, String tplId) {
        log.info("按指定模板发送短信验证码：phone: {}, verifyCode: {}", phone, verifyCode);

        Map<String, String> params = new HashMap<>(3);
        params.put("phone", phone);
        params.put("template_id", tplId);
        params.put("sign_id", smsProperties.getSignId());

        Map<String, Object> dataMap = new HashMap<>(3);
        dataMap.put("k1", verifyCode);
        dataMap.put("k2", smsProperties.getVerifyCodeEffectiveTime());
        params.put("datas", GsonUtil.toJson(dataMap));

        try {
            String response = HttpUtil.post(smsProperties.getDomain().concat("/sms/sms/send_platforms_sms/"), params);
            ResponseDTO responseDTO = GsonUtil.buildGson().fromJson(response, ResponseDTO.class);

            switch (responseDTO.getStatus().getCode()) {
                case ErrCode.SUCCESS:
                    return;
                case ErrCode.CHECK_FAIL:
                case ErrCode.CAPTCHA_EXPIRE:
                default:
                    throw new BaseException(ErrorCodeEnum._10009);
            }

        } catch (Exception e) {
            throw new BaseException(ErrorCodeEnum._10009);
        }
    }

    private static class ErrCode {
        private static final String SUCCESS = "0";
        private static final String CHECK_FAIL = "01520003";
        private static final String CAPTCHA_EXPIRE = "01520004";
    }

}
