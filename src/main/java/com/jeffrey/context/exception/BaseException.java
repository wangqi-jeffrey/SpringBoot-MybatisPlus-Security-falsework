package com.jeffrey.context.exception;

import com.jeffrey.context.enums.ErrorCodeEnum;
import lombok.Data;

/**
 * Description:
 *
 * @author 滕国栋
 * @date 2020/08/18 上午 11:27
 */
@Data
public class BaseException extends RuntimeException {
    private String errorCode;

    private String description;

    public BaseException(ErrorCodeEnum error) {
        super(error.getErrorMsg());
        this.errorCode = error.getErrorCode();
        this.description = error.getErrorMsg();
    }

    public BaseException(String errorCode, String description) {
        super(description);
        this.errorCode = errorCode;
        this.description = description;
    }
}
