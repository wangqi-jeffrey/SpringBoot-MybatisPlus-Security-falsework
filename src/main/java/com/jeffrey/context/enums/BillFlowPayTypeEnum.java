package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:账单流水支付方式
 *
 * @author YGC
 * @date 2020/08/29 16:23
 */
@Getter
@AllArgsConstructor
public enum BillFlowPayTypeEnum {

    WECHAT(1, "微信"),

    ALIPAY(2, "支付宝"),

    BANKCARD(3, "银行卡"),

    CASH(4, "现金"),

    CARD(5, "POS刷卡"),

    BALANCE(6, "已收平账"),

    CCB(7, "微信"),

    OTHER(8, "其他"),

    ;
    /**
     * 类型
     */
    private int type;

    /**
     * 描述
     */
    private String desc;

    /**
     * 通过类型获取枚举项
     *
     * @param type
     * @return
     */
    public static BillFlowPayTypeEnum getByType(int type) {
        for (BillFlowPayTypeEnum billFlowPayTypeEnum : BillFlowPayTypeEnum.values()) {
            if (billFlowPayTypeEnum.getType() == type) {
                return billFlowPayTypeEnum;
            }
        }

        return null;
    }
}
