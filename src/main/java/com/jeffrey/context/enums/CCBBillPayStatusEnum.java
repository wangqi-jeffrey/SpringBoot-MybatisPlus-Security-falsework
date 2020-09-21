package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  * Description:
 *  * @author 金泽强
 *  * @date 2020/08/26 2:03 下午
 *  
 */

@Getter
@AllArgsConstructor
public enum CCBBillPayStatusEnum {

    DEFAULT(0, "未同步"),
    PRE(1, "已下单"),
    SUCCESS(2, "支付成功"),
    FAILURE(3, "支付失败");

    private int value;
    private String desc;
}
