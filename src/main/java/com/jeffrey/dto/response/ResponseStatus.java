package com.jeffrey.dto.response;

import com.jeffrey.context.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description: 响应状态
 *
 * @author 滕国栋
 * @date 2020/08/18 上午 11:20
 */
@Data
@AllArgsConstructor
public class ResponseStatus {
    /**
     * 编号
     */
    private String code;

    /**
     * 描述信息
     */
    private String description;

    public ResponseStatus() {
        this(ErrorCodeEnum.SUCCESS.getErrorCode(), ErrorCodeEnum.SUCCESS.getErrorMsg());
    }
}
