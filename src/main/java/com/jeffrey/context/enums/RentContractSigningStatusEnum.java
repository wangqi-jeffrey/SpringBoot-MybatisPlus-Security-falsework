package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 出租合同签约状态
 *
 * @author TGD
 * @date 2020/08/21 下午 19:28
 */
@Getter
@AllArgsConstructor
public enum RentContractSigningStatusEnum {
    SIGNED(1, "已签约"),
    CHECKOUT(2, "已退租"),
    RENEWED(3, "已续租"),
    EXCHANGED(4, "已换租"),
    ;
    private int value;

    private String desc;
}
