package com.jeffrey.context.exception;

import com.jeffrey.context.enums.ErrorCodeEnum;
import lombok.Getter;

/**
 * Description: JwtAuthenticationException
 *
 * @author WQ
 * @date 2020/8/20 6:33 PM
 */
@Getter
public class JwtAuthenticationException extends org.springframework.security.core.AuthenticationException {

    private static final long serialVersionUID = 4084378563532902113L;

    private ErrorCodeEnum errorCodeEnum;

    private String code;

    private String userIdentity;

    public JwtAuthenticationException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getErrorMsg());
        this.code = errorCodeEnum.getErrorCode();
        this.errorCodeEnum = errorCodeEnum;
    }

    public JwtAuthenticationException(ErrorCodeEnum errorCodeEnum, String userIdentity) {
        super(errorCodeEnum.getErrorMsg());
        this.code = errorCodeEnum.getErrorCode();;
        this.userIdentity = userIdentity;
        this.errorCodeEnum = errorCodeEnum;
    }
}
