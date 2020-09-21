package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  * Description: 租客证件类型对应关系
 *  * @author 金泽强
 *  * @date 2020/09/01 10:09 下午
 *  
 */
@Getter
@AllArgsConstructor
public enum CCBCredentialsTypeEnum {

    IDENTITY_CARD(1, "01", "身份证"),
    PASSPORT(2, "07", "护照"),
    TAIWAN(3, "03", "台胞证"),
    HK_MACAO(4, "04", "港澳通行证");

    private Integer value;
    private String ccbValue;
    private String desc;

    public static CCBCredentialsTypeEnum getByValue(int value) {
        for (CCBCredentialsTypeEnum ccbCredentialsTypeEnum : CCBCredentialsTypeEnum.values()) {
            if (ccbCredentialsTypeEnum.getValue() == value) {
                return ccbCredentialsTypeEnum;
            }
        }
        return null;
    }

}
