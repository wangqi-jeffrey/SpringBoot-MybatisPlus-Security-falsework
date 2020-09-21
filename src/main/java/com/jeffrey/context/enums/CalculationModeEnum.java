package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 计算方式
 *
 * @author YGC
 * @date 2020/08/29 12:02
 */
@Getter
@AllArgsConstructor
public enum CalculationModeEnum {

    ADD(1, "加"),

    SUBTRACT(2, "减"),

    MULTIPLY(3, "乘"),

    DIVIDE(4, "除");

    /**
     * 计算方式
     */
    private int mode;

    /**
     * 描述
     */
    private String desc;
}
