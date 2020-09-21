package com.jeffrey.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Description: 日志处理工具类
 *
 * @author 滕国栋
 * @date 2020/08/17 下午 21:49
 */
public class LogUtil {

    /**
     * 获取请求日志
     *
     * @param request
     * @return
     */
    public static String getRequestLog(HttpServletRequest request) {
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
        return String.format("[REQUEST] mark=%s %s Accept[%s] %s", Thread.currentThread().getId(),
                request.getRequestURL(), request.getHeader("Accept"), sb.toString());
    }

    /**
     * 获取响应的日志
     *
     * @param request
     * @param response
     * @return
     */
    public static String getResponseLog(long costTime, String content, String url) {
        return String.format("[RESPONSE] mark=%s %s [costime=%s] %s", Thread.currentThread().getId(), url, costTime,
                content);
    }

    /**
     * 获取异常响应的日志
     *
     * @param content
     * @param url
     * @return
     */
    public static String getExceptionalResponseLog(String content, String url) {
        return String.format("[RESPONSE|EXCEPTION] mark=%s %s %s", Thread.currentThread().getId(), url, content);
    }

    /**
     * 获取通用的日志
     *
     * @param content
     * @return
     */
    public static String getCommLog(String content) {
        return String.format("[PROCESS] mark=%s %s", Thread.currentThread().getId(), content);
    }

    /**
     * 获取通用的日志
     *
     * @param content
     * @return
     */
    public static String getCommLog(String content, Object... replaceMsg) {
        return String.format("[PROCESS] mark=%s %s", Thread.currentThread().getId(),
                String.format(content, replaceMsg));
    }

    /**
     * 获取通用的日志
     *
     * @param e
     * @return
     */
    public static String getCommLog(Throwable e) {
        return String.format("[PROCESS] mark=%s %s", Thread.currentThread().getId(), ExceptionUtils.getStackTrace(e));
    }

    /**
     * 获取通用的日志
     *
     * @param e
     * @return
     */
    public static String getCommLog(String content, Throwable e) {
        return String.format("[PROCESS] mark=%s %s %s", Thread.currentThread().getId(), content, ExceptionUtils.getStackTrace(e));
    }
}
