package com.jeffrey.dto.request.user;

import com.jeffrey.dto.request.BaseRequestDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Description: B端管理员账户认证入参
 *
 * @author WQ
 * @date 2020/08/22 11:13 AM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ManagerAccountRequestDTO extends BaseRequestDTO {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "短信验证码")
    @NotBlank(message = "短信验证码不能为空")
    private String verificationCode;
}