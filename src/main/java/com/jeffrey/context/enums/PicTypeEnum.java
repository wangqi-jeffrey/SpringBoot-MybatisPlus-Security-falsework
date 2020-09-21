package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 *
 * @author yupeng
 * @date 2020/08/22 10:53
 */

@Getter
@AllArgsConstructor
public enum PicTypeEnum {

    ROOM_TYPE_PIC("10101", "房型图片"),
    PUBLIC_PIC("10102", "公区图片"),
    BILL_FLOW_VOUCHER_PIC("10103", "收款凭证图片"),
    ;

    private String value;

    private String desc;

}
