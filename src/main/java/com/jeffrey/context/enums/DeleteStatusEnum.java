package com.jeffrey.context.enums;

import lombok.Getter;

/**
 * Description:删除状态枚举
 *
 * @author YGC
 * @date 2020/08/19 21:45
 */
@Getter
public enum DeleteStatusEnum {

    NO(0, "未删除"),

    YES(1, "已删除");

    private int status;

    private String desc;

    DeleteStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
