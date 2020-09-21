package com.jeffrey.vo.wechat;

import lombok.Data;

import java.util.Date;

/**
 * Description: 催收消息
 *
 * @author WQ
 * @date 2020/09/11 11:05 AM
 */
@Data
public class CollectionMsgVO {

    /**
     * 租客手机号
     */
    private String phone;

    /**
     * 应还款金额
     */
    private Long amount;

    /**
     * 应还款时间
     */
    private Date collectionDate;
}