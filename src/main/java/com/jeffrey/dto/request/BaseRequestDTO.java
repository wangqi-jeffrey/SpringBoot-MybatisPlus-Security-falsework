package com.jeffrey.dto.request;

import com.jeffrey.context.enums.PlatformEnum;
import com.jeffrey.dto.ManagerAccountDTO;
import com.jeffrey.dto.UserInfoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: 请求入参顶级父类
 *
 * @author 滕国栋
 * @date 2020/08/18 上午 11:09
 */
@Data
public class BaseRequestDTO {
    /**
     * 客户端类型
     */
    @ApiModelProperty(value = "客户端类型(header)", required = true, hidden = true)
    private PlatformEnum platform;

    /**
     * 登录令牌
     */
    @ApiModelProperty(value = "登录令牌(header)", hidden = true)
    private String token;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号(header)", required = true, hidden = true)
    private Integer version;

    /**
     * 客户端ip
     */
    @ApiModelProperty(hidden = true)
    private String clientIp;

    /**
     * B端管理员账户信息
     */
    @ApiModelProperty(hidden = true)
    private ManagerAccountDTO managerAccountDTO;

    /**
     * C端租客信息
     */
    @ApiModelProperty(hidden = true)
    private UserInfoDTO userInfoDTO;
}
