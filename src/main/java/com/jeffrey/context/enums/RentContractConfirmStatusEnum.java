package com.jeffrey.context.enums;

import lombok.Getter;

/**
 * Description: 出租合同确认状态
 *
 * @author yupeng
 * @date 2020/08/20 21:23
 */

@Getter
public enum RentContractConfirmStatusEnum {

    NO(0, "待确认"),

    YES(1, "已确认");

    private int value;

    private String desc;

    RentContractConfirmStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
