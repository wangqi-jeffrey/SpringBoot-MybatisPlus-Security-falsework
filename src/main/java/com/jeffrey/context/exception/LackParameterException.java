package com.jeffrey.context.exception;


import com.jeffrey.context.enums.ErrorCodeEnum;

/**
 * Description: 缺少参数异常类
 * ClassName: BaseException
 * date: 2018年11月11日
 *
 * @author YGC
 * @since JDK 1.8
 */
public class LackParameterException extends BaseException {

    private static final long serialVersionUID = -7305673475606021068L;

    public LackParameterException(String description) {
        super(ErrorCodeEnum._10008.getErrorCode(), description);
    }
}
