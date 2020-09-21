package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 计算字段
 *
 * @author YGC
 * @date 2020/08/29 12:02
 */
@Getter
@AllArgsConstructor
public enum CalculationFieldEnum {

    AMOUNT(1, "总金额"),

    RECEIVED_AMOUNT(2, "已收金额"),

    ;

    /**
     * 计算字段
     */
    private int field;

    /**
     * 描述
     */
    private String desc;
}
