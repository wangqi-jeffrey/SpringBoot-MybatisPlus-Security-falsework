package com.jeffrey.manager;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.jeffrey.context.common.Builder;
import com.jeffrey.context.configuration.WxMaConfiguration;
import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.enums.OperateLogTypeEnum;
import com.jeffrey.context.exception.BaseException;
import com.jeffrey.context.properties.JwtTokenProperties;
import com.jeffrey.context.security.AuthenticationContextHolder;
import com.jeffrey.dto.UserInfoDTO;
import com.jeffrey.dto.request.user.UserLoginRequestDTO;
import com.jeffrey.dto.response.user.UserResponseDTO;
import com.jeffrey.service.UserService;
import com.jeffrey.utils.BeanMapUtils;
import com.jeffrey.utils.JwtTokenUtil;
import com.jeffrey.utils.LogUtil;
import com.jeffrey.utils.StringUtil;
import com.jeffrey.utils.json.GsonUtil;
import com.jeffrey.vo.user.OperateLogVO;
import com.jeffrey.vo.user.WxUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 统一鉴权
 *
 * @author WQ
 * @date 2020/08/21 4:10 PM
 */
@Slf4j
@Component
public class AuthenticationManager {

    @Autowired
    private UserService userService;

    @Autowired
    private SmsManager smsManager;

    @Autowired
    private JwtTokenProperties jwtTokenProperties;

    @Autowired
    private OperateLogManager operateLogManager;

    public UserResponseDTO getUserAccessToken(UserLoginRequestDTO userLoginRequestDTO) {
        WxUserInfoVO wxUserInfoVO = null;
        try {
            final WxMaService wxService = WxMaConfiguration.getMaService(userLoginRequestDTO.getAppid());
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(userLoginRequestDTO.getCode());
            String sessionKey = session.getSessionKey();
            String openid = session.getOpenid();
            String unionid = session.getUnionid();
            log.info("miniapp user login session key is {} ", sessionKey);
            log.info("miniapp user login openid is {} ", openid);
            log.info("miniapp user login unionid is {} ", unionid);

            // 解密用户信息，用户信息暂时无法获取
            // WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, userLoginRequestDTO.getEncryptedData(),
            //         userLoginRequestDTO.getIv());
            // log.info("miniapp user login userInfo is {} ", GsonUtil.toJson(userInfo));

            // 获取用户手机号
            WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, userLoginRequestDTO.getEncryptedData(),
                    userLoginRequestDTO.getIv());
            log.info("miniapp user login phoneNoInfo is {} ", GsonUtil.toJson(phoneNoInfo));

            if (StringUtil.isBlank(phoneNoInfo.getPhoneNumber())) {
                log.info("miniapp user login phoneNumber is null");
                throw new BaseException(ErrorCodeEnum._10027);
            }

            wxUserInfoVO = new WxUserInfoVO();
            // BeanUtils.copyProperties(userInfo, wxUserInfoVO);
            wxUserInfoVO.setOpenId(openid);
            wxUserInfoVO.setUnionId(unionid);
            wxUserInfoVO.setPhone(phoneNoInfo.getPhoneNumber());
            wxUserInfoVO.setAppid(userLoginRequestDTO.getAppid());

        } catch (Exception e) {
            log.info(LogUtil.getCommLog("验证用户身份失败", e));
            throw new BaseException(ErrorCodeEnum._10027);
        }
        // 用户登录
        UserResponseDTO userResponseDTO = userService.getUserInfoAngLogin(wxUserInfoVO);
        // 生成token
        userResponseDTO.setAuthorities(userResponseDTO.obtainRoles());
        String subject = userResponseDTO.getPhone();
        Map<String, Object> claims = BeanMapUtils.beanToMap(userResponseDTO);
        String accessToken = JwtTokenUtil.generateToken(subject, claims, jwtTokenProperties.getTokenExpiredTimeMiniApp());
        userResponseDTO.setToken(accessToken);

        // 增加操作日志
        operateLogManager.log(Builder.of(OperateLogVO::new)
                .with(OperateLogVO::setOperateLogTypeEnum, OperateLogTypeEnum.USER_CUSTOMER_LOGIN)
                .with(OperateLogVO::setBusinessKey, userResponseDTO.getUserCode())
                .with(OperateLogVO::setOperatorCode, userResponseDTO.getUserCode())
                .with(OperateLogVO::setContent, "小程序登录")
                .build());
        return userResponseDTO;
    }

    public void deleteUserAccessToken() {
        UserInfoDTO userInfoDTO = AuthenticationContextHolder.getUserInfo();
        operateLogManager.log(Builder.of(OperateLogVO::new)
                .with(OperateLogVO::setOperateLogTypeEnum, OperateLogTypeEnum.USER_CUSTOMER_LOGOUT)
                .with(OperateLogVO::setBusinessKey, userInfoDTO.getUserCode())
                .with(OperateLogVO::setContent, "小程序退出登录")
                .build());
    }
}