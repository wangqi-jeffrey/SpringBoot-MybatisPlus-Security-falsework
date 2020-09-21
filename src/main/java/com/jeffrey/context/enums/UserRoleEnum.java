package com.jeffrey.context.enums;

import lombok.Getter;

/**
 * Description: 系统用户角色
 *
 * @author WQ
 * @date 2020/8/24 9:50 AM
 */
@Getter
public enum UserRoleEnum {
    /**
     * 房东
     */
    ROLE_LANDLORD("LA"),
    /**
     * 管家
     */
    ROLE_BUTLER("BU"),
    /**
     * 租客
     */
    ROLE_TENANT("TE");

    private String prefix;

    UserRoleEnum(String prefix) {
        this.prefix = prefix;
    }
}
