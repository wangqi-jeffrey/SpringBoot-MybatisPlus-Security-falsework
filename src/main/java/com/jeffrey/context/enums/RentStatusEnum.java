package com.jeffrey.context.enums;

import lombok.Getter;

/**
 * Description: 出租状态
 *
 * @author yupeng
 * @date 2020/08/20 21:23
 */

@Getter
public enum RentStatusEnum {

    NO(0, "未租"),

    YES(1, "已租");

    private int status;

    private String desc;

    RentStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
