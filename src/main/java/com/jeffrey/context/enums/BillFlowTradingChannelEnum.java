package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:账单流水交易渠道
 *
 * @author YGC
 * @date 2020/08/29 16:23
 */
@Getter
@AllArgsConstructor
public enum BillFlowTradingChannelEnum {

    OFFLINE(1, "线下"),

    ONLINE(2, "线上");
    /**
     * 状态
     */
    private int channel;

    /**
     * 描述
     */
    private String desc;
}
