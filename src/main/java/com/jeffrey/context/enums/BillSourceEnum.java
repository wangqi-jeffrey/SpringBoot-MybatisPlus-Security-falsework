package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:账单来源
 *
 * @author YGC
 * @date 2020/08/27 21:23
 */
@Getter
@AllArgsConstructor
public enum BillSourceEnum {

    MANUAL(1, "手动添加"),

    RENT_CONTRACT(2, "租约"),

    METER_READING(3, "抄表"),

    CHECKOUT(4, "退租");

    /**
     * 状态
     */
    private int source;

    /**
     * 描述
     */
    private String desc;
}
