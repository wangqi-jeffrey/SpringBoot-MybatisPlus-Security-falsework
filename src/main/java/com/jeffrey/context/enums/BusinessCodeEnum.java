package com.jeffrey.context.enums;

import lombok.Getter;

/**
 * Description:业务编号枚举
 *
 * @author YGC
 * @date 2020/08/20 11:45
 */
@Getter
public enum BusinessCodeEnum {

    FOCUS_HOUSE_CODE_PREFIX("FH", "集中式房源编号前缀"),

    FOCUS_ROOM_CODE_PREFIX("FR", "集中式房间编号前缀"),

    FOCUS_ROOM_TYPE_CODE_PREFIX("FT", "集中式房型编号前缀"),

    RENT_LEASE_CODE_PREFIX("RL", "租约编号前缀"),

    RENT_CONTRACT_CODE_PREFIX("RC", "出租合同编号前缀"),

    RENT_CONTRACT_TENANT_CODE_PREFIX("RCT", "出租合同租客编号前缀"),

    BILL_CODE_PREFIX("BL", "账单编号前缀"),

    BILL_DETAILS_CODE_PREFIX("BI", "账单明细编号前缀"),

    BILL_FLOW_CODE_PREFIX("BF", "账单流水编号前缀"),

    BILL_FLOW_PAY_ORDER_CODE_PREFIX("BFP", "线下交易支付订单编号前缀"),

    BILL_FLOW_ITEM_CODE_PREFIX("BFI", "账单流水明细编号前缀"),

    METER_READING_CODE_PREFIX("MRR", "抄表记录编号前缀"),

    CCB_BILL_CODE_PREFIX("CBL", "建融账单编号前缀"),
    ;

    private String prefix;

    private String desc;

    BusinessCodeEnum(String prefix, String desc) {
        this.prefix = prefix;
        this.desc = desc;
    }
}
