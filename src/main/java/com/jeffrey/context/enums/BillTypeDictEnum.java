package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:账单类型
 *
 * @author yupeng
 * @date 2020/08/22 10:53
 */
@Getter
@AllArgsConstructor
public enum BillTypeDictEnum {

    /**
     * 一级字典
     */
    RENT("10501", 0, "租金"),

    DEPOSIT("10502", 0, "押金"),

    METER("10503", 0, "水电燃"),

    SERVICE("10504", 0, "服务费"),

    CHECKOUT("10524", 0, "退租"),

    /**
     * 二级字典
     */
    RENT_SUB("10521", 0, "租金"),

    DEPOSIT_SUB("10522", 0, "押金"),

    COLD_WATER_SUB("10513", 1, "冷水费"),

    HOT_WATER_SUB("10514", 1, "热水费"),

    ELECTRIC_SUB("10515", 1, "电费"),

    GAS_SUB("10523", 1, "燃气费"),

    /**
     * 字典类型
     */
    BILL_TYPE_DICT("bill_type", 0, "全部账单类型"),

    ;


    /**
     * 编号
     */
    private String code;

    /**
     * 是否可删除：0-否 1-是
     */
    private int canDelete;

    /**
     * 描述
     */
    private String desc;


    /**
     * 获取不可删除的字典类型编号
     *
     * @return
     */
    public static List<String> getCanNotDelDictCodes() {
        List<String> codes = new ArrayList<>();
        for (BillTypeDictEnum billTypeDictEnum : BillTypeDictEnum.values()) {
            if (billTypeDictEnum.getCanDelete() == 0)
                codes.add(billTypeDictEnum.getCode());
        }

        return codes;
    }

    public static BillTypeDictEnum of(String code) {
        for (BillTypeDictEnum billTypeDictEnum : BillTypeDictEnum.values()) {
            if (billTypeDictEnum.getCode().equals(code)) {
                return billTypeDictEnum;
            }
        }

        return null;
    }
}
