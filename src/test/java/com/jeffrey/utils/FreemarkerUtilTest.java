package com.jeffrey.utils;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FreemarkerUtilTest {

    @Test
    void parseTplToPdf() {
    }

    @Test
    void parseTplToHtml() {
        String htmlUrl = FreemarkerUtil.parseTplToHtml("pay_result", "00001", new HashMap<>() {
            {
                put("pay_status", 2);
                put("business_order_no", "No00000001");
            }
        });
        Assert.assertNotNull(htmlUrl);
        System.out.println(htmlUrl);
    }

    @Test
    void htmlToPdf() {
        Map<String, Object> param = Maps.newHashMap();
        param.put("contractCode", "00001");
        param.put("landlordName", "房东姓名-大王");
        param.put("landlordPhone", "131111111111");
        param.put("landlordAddress", "房东地址-华控大厦大厦大厦");
        param.put("tenantName", "租客姓名-小王");
        param.put("tenantPhone", "13999999999");
        param.put("tenantGender", "男");
        param.put("tenantCredentialsType", "身份证");
        param.put("tenantCredentialsNo", "租客证件号-111111111111");
        param.put("tenantAddress", "租客地址-中国华北华北华北");
        param.put("leaseBeginDate", "2020-01-12"); // 租约开始日期
        param.put("leaseEndDate", "2020-11-12"); // 租约结束日期
        param.put("leaseDuration", "一年两月"); // 租约期长
        param.put("paymentMode", "季付"); // 租金付款方式
        param.put("paymentModeDesc", "季"); // 月/季/年
        param.put("rentTerm", "4"); // 期数
        param.put("depositPrice", "1000"); // 押金金额
        param.put("depositPriceUp", "壹仟元整"); // 押金金额大写
        param.put("rentPrice", "1000"); // 每月房租金额
        param.put("rentPriceUp", "壹仟元整"); // 每月房租金额大写
        param.put("cycleFee", "2000"); // 每月周期性费用总额
        param.put("cycleFeeUp", "2000"); // 每月周期性费用总额大写
        param.put("houseArea", "198.0"); // 房源面积

        param.put("payRentRule", "每期交租日之前付款"); // 交租日规则
        param.put("everyPeriodPayDate", "提前3天"); // 每期交租日明细
        param.put("contractSettings", "电视机（1234.00元）、床（2222.00元）、冰箱（4356.00元）"); // 房间家具清单

        String htmlUrl = FreemarkerUtil.parseTplToHtml("rent-contract", "00001", param);
        String pdfUrl = FreemarkerUtil.htmlToPdf(htmlUrl, "00001");
        System.out.println("htmlUrl: " + htmlUrl);
        System.out.println("pdfUrl: " + pdfUrl);
    }
}