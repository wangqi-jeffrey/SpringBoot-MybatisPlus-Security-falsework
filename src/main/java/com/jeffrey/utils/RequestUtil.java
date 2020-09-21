package com.jeffrey.utils;

import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.BaseException;
import com.jeffrey.context.exception.LackParameterException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 请求处理工具类
 *
 * @author 滕国栋
 * @date 2020/08/17 下午 21:49
 */
public class RequestUtil {

    // 代理头域名称
    private static final String[] PROXY_REMOTE_IP_ADDRESS = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP"};

    private static final String UNKNOWN = "unknown";

    /**
     * 获取参数
     *
     * @param request
     * @param key
     * @return
     * @throws Exception
     */
    public static String getParameterString(HttpServletRequest request, String key) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            throw new BaseException(ErrorCodeEnum._10008.getErrorCode(), "缺少参数:" + key);
        }
        return value.trim();
    }

    /**
     * 获取参数
     *
     * @param request
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static String getParameterString(HttpServletRequest request, String key, String defaultValue)
            throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value.trim();
    }

    /**
     * 获取参数
     *
     * @param request
     * @param key
     * @return
     * @throws Exception
     */
    public static long getParameterLong(HttpServletRequest request, String key) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            throw new LackParameterException("缺少参数:" + key);
        }
        Long longValue = null;
        try {
            longValue = Long.valueOf(value.trim());
        } catch (Exception e) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return longValue;
    }

    /**
     * 获取参数
     *
     * @param request
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static long getParameterLong(HttpServletRequest request, String key, long defaultValue) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        Long longValue = null;
        try {
            longValue = Long.valueOf(value.trim());
        } catch (Exception e) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return longValue;
    }

    /**
     * 获取参数
     *
     * @param request
     * @param key
     * @return
     * @throws Exception
     */
    public static int getParameterInt(HttpServletRequest request, String key) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            throw new LackParameterException("缺少参数:" + key);
        }
        Integer intValue = null;
        try {
            intValue = Integer.valueOf(value.trim());
        } catch (Exception e) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return intValue;
    }

    /**
     * 获取参数
     *
     * @param request
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static int getParameterInt(HttpServletRequest request, String key, int defaultValue) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        Integer intValue = null;
        try {
            intValue = Integer.valueOf(value.trim());
        } catch (Exception e) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return intValue;
    }

    /**
     * 获取double参数
     *
     * @param request
     * @param key
     * @return
     * @throws Exception
     */
    public static double getParameterDouble(HttpServletRequest request, String key) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            throw new LackParameterException("缺少参数:" + key);
        }
        Double doubleValue = null;
        try {
            doubleValue = Double.valueOf(value.trim());
        } catch (Exception e) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return doubleValue;
    }

    /**
     * 获取double参数
     *
     * @param request
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static double getParameterDouble(HttpServletRequest request, String key, int defaultValue) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        Double doubleValue = null;
        try {
            doubleValue = Double.valueOf(value.trim());
        } catch (Exception e) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return doubleValue;
    }

    /**
     * 获取float参数
     *
     * @param request
     * @param key
     * @return
     * @throws Exception
     */
    public static float getParameterFloat(HttpServletRequest request, String key) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            throw new LackParameterException("缺少参数:" + key);
        }
        Float floatValue = null;
        try {
            floatValue = Float.valueOf(value.trim());
        } catch (Exception e) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return floatValue;
    }

    /**
     * 获取float参数
     *
     * @param request
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static float getParameterFloat(HttpServletRequest request, String key, int defaultValue) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        Float floatValue = null;
        try {
            floatValue = Float.valueOf(value.trim());
        } catch (Exception e) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return floatValue;
    }

    /**
     * 获取日期参数
     *
     * @param request
     * @param key
     * @return
     * @throws Exception
     */
    public static Date getParameterDate(HttpServletRequest request, String key) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            throw new LackParameterException("缺少参数:" + key);
        }
        Date parseDate = DateUtil.parseDate(value);
        if (parseDate == null) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return parseDate;
    }

    /**
     * 获取日期时间参数
     *
     * @param request
     * @param key
     * @return
     * @throws Exception
     */
    public static Date getParameterDateTime(HttpServletRequest request, String key) throws Exception {
        String value = request.getParameter(key);

        if (StringUtils.isBlank(value)) {
            throw new LackParameterException("缺少参数:" + key);
        }
        Date parseDate = DateUtil.parseDateTime(value);
        if (parseDate == null) {
            throw new InvalidParameterException("参数异常:" + key);
        }
        return parseDate;
    }

    /**
     * 获取API参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getApiParams(HttpServletRequest request) {
        Map<String, String> paramsMap = new HashMap<>();
        // 从HtpServletRequest中解析原始参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String value = "";
            if (entry.getValue() != null && entry.getValue().length > 0) {
                value = entry.getValue()[0];
            }

            // 参数放入的时候对两端的空格做处理
            paramsMap.put(entry.getKey(), value);
        }
        return paramsMap;
    }

    /**
     * 将请求参数转换成字符串
     *
     * @param request
     * @return
     */
    public static String getRequest4String(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        // 获取所有的参数
        sb.append("{");
        Enumeration<String> parameters = request.getParameterNames();
        if (parameters != null) {
            while (parameters.hasMoreElements()) {
                String name = parameters.nextElement();
                sb.append(name).append("=").append(request.getParameter(name)).append("; ");
            }
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * 获取客户端地址
     *
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        for (int i = 0; i < PROXY_REMOTE_IP_ADDRESS.length; i++) {
            String ip = request.getHeader(PROXY_REMOTE_IP_ADDRESS[i]);
            if (StringUtil.isNotBlank(ip) && !ip.trim().equalsIgnoreCase(UNKNOWN)) {
                return getRemoteIpFromForward(ip.trim());
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取多个地址中的第一个地址
     *
     * @param xforwardIp
     * @return
     */
    private static String getRemoteIpFromForward(String xforwardIp) {
        int commaOffset = xforwardIp.indexOf(',');
        if (commaOffset < 0) {
            return xforwardIp;
        }
        return xforwardIp.substring(0, commaOffset);
    }

    /**
     * 获取参数
     *
     * @param request
     * @param key
     * @return
     */
    public static long getAttributeLong(HttpServletRequest request, String key) {
        Long value = (Long) request.getAttribute(key);
        if (value == null) {
            throw new LackParameterException("缺少参数:" + key);
        }
        return value;
    }

}
