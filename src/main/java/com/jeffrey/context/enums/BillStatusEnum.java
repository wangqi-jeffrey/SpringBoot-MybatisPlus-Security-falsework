package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:账单状态
 *
 * @author YGC
 * @date 2020/08/27 21:23
 */
@Getter
@AllArgsConstructor
public enum BillStatusEnum {

    DUEIN(0, "待收款"),

    RECEIVED(1, "已收款"),

    SETTLED(2, "已结清"),

    PARTIALLY_RECEIVED(3, "部分收款");

    /**
     * 状态
     */
    private int status;

    /**
     * 描述
     */
    private String desc;
}
