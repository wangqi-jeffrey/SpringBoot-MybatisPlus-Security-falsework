package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:阶梯价配置
 *
 * @author yupeng
 * @date 2020/09/02 12:16
 */
@Getter
@AllArgsConstructor
public enum PriceConfigEnum {

    ISNOTLADDER(0, "非阶梯价"),

    ISLADDER(1, "是阶梯价");

    /**
     * 状态
     */
    private int status;

    /**
     * 描述
     */
    private String desc;

}
