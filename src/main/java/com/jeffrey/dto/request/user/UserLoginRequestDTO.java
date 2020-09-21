package com.jeffrey.dto.request.user;

import com.jeffrey.dto.request.BaseRequestDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Description: C端租客小程序登录入参
 *
 * @author WQ
 * @date 2020/8/28 3:51 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginRequestDTO extends BaseRequestDTO {

    @ApiModelProperty(value = "小程序appid")
    @NotBlank(message = "appid不能为空")
    private String appid;

    @ApiModelProperty(value = "临时登录凭证code")
    @NotBlank(message = "code不能为空")
    private String code;

    @ApiModelProperty(value = "加密数据encryptedData")
    @NotBlank(message = "encryptedData不能为空")
    private String encryptedData;

    @ApiModelProperty(value = "对称解密算法初始向量iv")
    @NotBlank(message = "iv不能为空")
    private String iv;

}