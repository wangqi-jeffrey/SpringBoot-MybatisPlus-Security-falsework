package com.jeffrey.dto.response.user;

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
 * Description: B端管理员账户认证成功出参
 *
 * @author WQ
 * @date 2020/08/22 11:03 AM
 */
@Data
public class ManagerAccountResponseDTO implements Authorityable {

    @ApiModelProperty(value = "管理员编号")
    private String managerCode;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "账户类型：0-房东 1-管家")
    private Integer accountType;

    @ApiModelProperty(value = "用户状态：0:有效；1:无效")
    private Integer status;

    @ApiModelProperty(value = "是否是内部账户，0：否 1：是")
    private Integer isInternal;

    @ApiModelProperty(value = "上次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "身份令牌")
    private String token;

    @ApiModelProperty(value = "权限集合")
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<GrantedAuthority> obtainRoles() {
        String authorityName = "";
        switch (this.accountType) {
            case 0:
                authorityName = UserRoleEnum.ROLE_LANDLORD.name();
                break;
            case 1:
                authorityName = UserRoleEnum.ROLE_BUTLER.name();
                break;
        }
        return Collections.singleton(new MySimpleGrantedAuthority(authorityName));
    }
}