package com.jeffrey.vo.payment;

import lombok.Data;

import java.util.Date;

/**
 * Description: 会支付支付结果通知参数
 *
 * @author WQ
 * @date 2020/9/4 7:24 PM
 */
@Data
public class PayNotifyVO {
    private String merchantId;

    /**
     * 支付订单号 -必须，tradeNo
     */
    private String busiNo;

    /**
     * 支付流水号 -必须
     */
    private String flowNo;

    private Integer payResult;

    /**
     * 支付金额
     */
    private Long paySuccessAmount;

    private Long orderAmount;

    /**
     * 优惠金额
     */
    private Long discountAmount;

    /**
     * 用户承担手续费，不同支付方式可能不同，单位分
     */
    private Long poundageAmount;

    /**
     * 支付完成时间 -必须
     */
    private Date counterofferTime;

    private Date settleTime;

    private String productType;

    private String productTypeCode;

    private String bankcardInfo;

    private String payChannel;

}