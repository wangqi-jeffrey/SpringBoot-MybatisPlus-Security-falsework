package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 *
 * @author YGC
 * @date 2020/09/10 10:20 下午
 */
@Getter
@AllArgsConstructor
public enum CashierTypeEnum {

    INCOME(1, "收入"),

    EXPENDITURE(2, "支出");

    private int type;

    private String desc;
}
