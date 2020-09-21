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
public enum PriceDictConfigEnum {

    WATER("10401", "冷水价"),

    HOTWATER("10402", "热水价"),

    ELECTRICITY_PRICE("10403", "电价"),

    DEPOSIT_SUB("10404", "燃气价");

    /**
     * 状态
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 通过字典编号获取字典项
     *
     * @param code
     * @return
     */
    public static PriceDictConfigEnum getByCode(String code) {
        for (PriceDictConfigEnum priceDictConfigEnum : PriceDictConfigEnum.values()) {
            if (priceDictConfigEnum.getCode().equals(code)) {
                return priceDictConfigEnum;
            }
        }

        return null;
    }

}
