package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 会支付支付方式
 *
 * @author WQ
 * @date 2020/9/5 7:06 PM
 */
@Getter
@AllArgsConstructor
public enum HzfPayTypeEnum {

    P002("P002", "银行卡快捷支付-借", BillFlowPayTypeEnum.BANKCARD),
    P005("P005", "支付宝-APP支付", BillFlowPayTypeEnum.ALIPAY),
    P006("P006", "微信支付-APP支付", BillFlowPayTypeEnum.WECHAT),
    P007("P007", "支付宝-扫码支付", BillFlowPayTypeEnum.ALIPAY),
    P008("P008", "微信支付-扫码支付", BillFlowPayTypeEnum.WECHAT),
    P009("P009", "微信公众号支付", BillFlowPayTypeEnum.WECHAT),
    P010("P010", "支付宝手机网站支付(支付宝WAP支付)", BillFlowPayTypeEnum.ALIPAY),
    P011("P011", "微信H5支付", BillFlowPayTypeEnum.WECHAT),
    P019("P019", "银联云闪付-H5", BillFlowPayTypeEnum.BANKCARD),
    P022("P022", "微信小程序支付", BillFlowPayTypeEnum.WECHAT),
    P023("P023", "银行卡快捷支付-贷(信用卡)", BillFlowPayTypeEnum.BANKCARD),
    P027("P027", "信用分期付", BillFlowPayTypeEnum.BANKCARD),
    P028("P028", "信用卡分期（订单金额大于等于600元才能使用信用卡分期）", BillFlowPayTypeEnum.BANKCARD),
    P029("P029", "花呗分期（订单金额大于等于600元才能使用花呗分期）", BillFlowPayTypeEnum.ALIPAY),
    OTHER("OTHER", "未匹配到", BillFlowPayTypeEnum.OTHER),

    ;
    /**
     * 类型
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    private BillFlowPayTypeEnum group;

    public static HzfPayTypeEnum of(String code) {
        for (HzfPayTypeEnum hzfPayTypeEnum : HzfPayTypeEnum.values()) {
            if (hzfPayTypeEnum.code.equals(code)) {
                return hzfPayTypeEnum;
            }
        }
        return HzfPayTypeEnum.OTHER;
    }
}
