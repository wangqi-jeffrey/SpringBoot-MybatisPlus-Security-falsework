package com.jeffrey.vo.payment;

import lombok.Data;

@Data
public class SyncUserInfoParamVo {
    //合同id
    private String contractId;
    //账单的期次
    private String period;
    //姓名
    private String name;
    //⼿机号
    private String phone;
    //身份证件照类型 ，身份证类型：01
    private String cardType;
    //身份证件号
    private String cardNum;
}