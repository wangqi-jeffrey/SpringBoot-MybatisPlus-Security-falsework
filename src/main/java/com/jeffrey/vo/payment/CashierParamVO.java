package com.jeffrey.vo.payment;

import lombok.Data;

/**
 * Description: 收银台参数
 *
 * @author WQ
 * @date 2020/08/31 10:04 PM
 */
@Data
public class CashierParamVO {

    private String tradeNo;

    private String openId;

    private String productName;

    private String productInfo;

    private String userId;

    private Long orderAmount;

    private String clientIp;

    private String successUrl;
}