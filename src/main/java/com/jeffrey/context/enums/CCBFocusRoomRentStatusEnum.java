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
public enum CCBFocusRoomRentStatusEnum {

    NO(0, "不可租"),
    YES(1, "可租"),
    LEASED(2, "已出租");

    private int value;
    private String desc;
}
