package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  * Description:
 *  * @author 金泽强
 *  * @date 2020/08/26 11:36 上午
 *  
 */

@Getter
@AllArgsConstructor
public enum CCBProjectTypeEnum {

    FOCUS(1, "集中式"),
    DISTRIBUTED(2, "分散式");

    private int value;
    private String desc;
}
