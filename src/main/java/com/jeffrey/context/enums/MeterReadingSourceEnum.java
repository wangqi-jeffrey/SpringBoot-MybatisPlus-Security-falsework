package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;

/**
 * Description: 抄表读书来源
 *
 * @author TGD
 * @date 2020/09/01 下午 14:37
 */
@AllArgsConstructor
public enum MeterReadingSourceEnum {
    MANUAL(0, "手抄"),

    RENT(1, "办理入住"),

    CHECKOUT(2, "租客退租"),

    AUTOMATIC(3, "自动抄表");


    private int status;

    private String desc;

    public int getStatus() {
        return status;
    }
}
