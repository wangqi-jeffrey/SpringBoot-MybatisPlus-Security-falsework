package com.jeffrey.dto.response.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: 房东账户信息出参
 *
 * @author WQ
 * @date 2020/9/7 10:42 AM
 */
@Data
public class LandlordInfoResponseDTO {

    @ApiModelProperty(value = "房东编号")
    private String landlordCode;

    @ApiModelProperty(value = "购买间数")
    private Integer roomNum;

    @ApiModelProperty(value = "收款账户类型：0-对私 1-对公")
    private Integer payeeAccountType;

    @ApiModelProperty(value = "收款账户开户银行")
    private String payeeBankName;

    @ApiModelProperty(value = "收款账户开户支行")
    private String payeeBranchBankName;

    @ApiModelProperty(value = "收款账户银行户名")
    private String payeeAccountName;

    @ApiModelProperty(value = "收款账户银行卡号")
    private String payeeAccountNo;

}