package com.jeffrey.manager;

import com.jeffrey.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 业务编号管理
 *
 * @author TGD
 * @date 2020/08/20 上午 11:48
 */
@Component
public class BusinessCodeManager {
    private static final int STEP = 1;

    private static final int DEFAULT_TOTAL_LENGTH = 4;

    private static final String ID_KEY = "uniqueno";

    @Autowired
    private CacheManager cacheManager;

    /**
     * 获取唯一编号
     *
     * @param prefix
     * @param length
     * @return
     */
    public String reqUniqueNo(String prefix, int length) {
        if (length < 4) {
            length = DEFAULT_TOTAL_LENGTH;
        }

        //前缀 + yyyyMMddHHmmssSSS
        String prefixAndDateWithTime = getPrefixAndDateWithTime(prefix);

        //后缀
        String coverId = String.format("%0" + length + "d", getIdByCoverLength(length));

        return prefixAndDateWithTime + coverId;
    }

    /**
     * 获取前缀加当前时间的日期
     *
     * @param prefix 前缀
     * @return 前缀加年月日 时分秒
     */
    private static String getPrefixAndDateWithTime(String prefix) {
        StringBuffer sb = new StringBuffer();
        if (null != prefix) {
            sb.append(prefix);
        }
        return sb.append(DateUtil.formatCurrentSystemDate()).toString();
    }

    /**
     * 获取全局唯一编号 前缀 + yyyyMMdd + 后缀
     *
     * @param prefix 前缀
     * @param length 后缀长度 最低4位
     * @return
     */
    public String reqUniqueNoShort(String prefix, int length) {
        if (length < 4) {
            length = DEFAULT_TOTAL_LENGTH;
        }

        //前缀 + yyyyMMdd
        String prefixAndDateWithTime = getPrefixAndDateWithToday(prefix);

        //后缀
        String coverId = String.format("%0" + length + "d", getIdByCoverLength(length));

        return prefixAndDateWithTime + coverId;
    }

    /**
     * 获取前缀加当前时间的日期
     *
     * @param prefix 前缀
     * @return 前缀加年月日 时分秒
     */
    private static String getPrefixAndDateWithToday(String prefix) {
        StringBuffer sb = new StringBuffer();
        if (null != prefix) {
            sb.append(prefix);
        }
        return sb.append(DateUtil.formatTodaySystemDate()).toString();
    }

    /**
     * 根据补位长度生成id
     *
     * @param coverLength 补位长度
     * @return 补位后的id
     */
    private long getIdByCoverLength(int coverLength) {
        //全局编号key
        String key = cacheManager.getKey(ID_KEY);
        long id = cacheManager.incr(key, STEP);
        String maxValue = getMaxValue(coverLength, "");
        long coverMaxValueLong = Long.parseLong(maxValue);
        String s = String.valueOf(id);
        return id < coverMaxValueLong ? id : Long.valueOf(s.substring(s.length() - coverLength));
    }

    /**
     * 生成最大值
     *
     * @param digit 位数
     * @param value value
     * @return 指定位数后的最大值
     */
    private static String getMaxValue(int digit, String value) {
        if (digit < 1) {
            return value;
        }
        return getMaxValue(--digit, value + "9");
    }
}
