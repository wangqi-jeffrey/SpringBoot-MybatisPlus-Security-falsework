package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  * Description: 建融合同签约类型
 *  * @author 金泽强
 *  * @date 2020/09/01 5:34 下午
 *  
 */

@Getter
@AllArgsConstructor
public enum CCBContractSigningStatusEnum {

    SIGNED(1, "已签约"),
    CLOSED(2, "已关闭"),
    ;
    private int value;

    private String desc;
}
