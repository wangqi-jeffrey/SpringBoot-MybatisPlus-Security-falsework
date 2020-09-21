package com.jeffrey.context.exception;

import com.jeffrey.context.enums.ErrorCodeEnum;

/**
 * @author xbr
 * @Description
 * @ClassName LockException
 * @Date 2018/12/17
 * @Version 1.0
 * @since JDK 1.8
 */
public class LockException extends BaseException {

    private static final long serialVersionUID = -6180551827710466809L;

    public LockException(ErrorCodeEnum error) {
        super(error);
    }
}
