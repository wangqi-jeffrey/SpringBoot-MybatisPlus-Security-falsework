package com.jeffrey.dto.response.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeffrey.context.enums.UserRoleEnum;
import com.jeffrey.context.security.Authorityable;
import com.jeffrey.context.security.MySimpleGrantedAuthority;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Description: C端用户认证成功出参
 *
 * @author WQ
 * @date 2020/8/29 10:17 AM
 */
@Data
public class UserResponseDTO implements Authorityable {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String appid;

    @JsonIgnore
    private String openId;

    @ApiModelProperty(value = "用户编号")
    private String userCode;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像图片的 URL")
    private String avatarUrl;

    @ApiModelProperty(value = "上次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "身份令牌")
    private String token;

    @ApiModelProperty(value = "权限集合")
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<GrantedAuthority> obtainRoles() {
        String authorityName = UserRoleEnum.ROLE_TENANT.name();
        return Collections.singleton(new MySimpleGrantedAuthority(authorityName));
    }
}