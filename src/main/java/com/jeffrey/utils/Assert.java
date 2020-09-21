package com.jeffrey.utils;

import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Description: 断言
 *
 * @author: 滕国栋
 * @date: 2019/8/21
 */
@Slf4j
public class Assert {
    /**
     * 状态断言
     *
     * @param expression
     * @param errorCode
     */
    public static void state(boolean expression, ErrorCodeEnum errorCode) {
        if (!expression) {
            log.error(LogUtil.getCommLog(ExceptionUtils.getStackTrace(new RuntimeException())));
            throw new BaseException(errorCode);
        }
    }

    /**
     * 状态断言
     *
     * @param expression
     * @param message
     * @param errorCode
     */
    public static void state(boolean expression, String message, ErrorCodeEnum errorCode) {
        if (!expression) {
            log.error(LogUtil.getCommLog(message));
            log.error(LogUtil.getCommLog(ExceptionUtils.getStackTrace(new RuntimeException())));
            throw new BaseException(errorCode);
        }
    }

    /**
     * 状态断言
     *
     * @param expression
     * @param message
     * @param errorCode
     */
    public static void state(boolean expression, String message, ErrorCodeEnum errorCode, String errorMsg) {
        if (!expression) {
            log.error(LogUtil.getCommLog(message));
            log.error(LogUtil.getCommLog(ExceptionUtils.getStackTrace(new RuntimeException())));
            throw new BaseException(errorCode.getErrorCode(), errorMsg);
        }
    }

    /**
     * 非空断言
     *
     * @param obj
     */
    public static void notNull(Object obj) {
        notNull(obj, ErrorCodeEnum._10002);
    }

    /**
     * 非空断言
     *
     * @param obj
     * @param errorCode
     */
    public static void notNull(Object obj, ErrorCodeEnum errorCode) {
        if (obj == null) {
            log.error(LogUtil.getCommLog(ExceptionUtils.getStackTrace(new RuntimeException())));
            throw new BaseException(errorCode);
        }
    }

    /**
     * 非空断言
     *
     * @param obj
     * @param message
     * @param errorCode
     */
    public static void notNull(Object obj, String message, ErrorCodeEnum errorCode) {
        if (obj == null) {
            log.error(LogUtil.getCommLog(message));
            log.error(LogUtil.getCommLog(ExceptionUtils.getStackTrace(new RuntimeException())));
            throw new BaseException(errorCode.getErrorCode(), message);
        }
    }
}
