package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 出租合同签约类型
 *
 * @author TGD
 * @date 2020/08/21 下午 19:28
 */
@Getter
@AllArgsConstructor
public enum RentContractSigningTypeEnum {
    NEW(1, "新签"),
    RENEW(2, "续签");
    private int value;

    private String desc;
}
