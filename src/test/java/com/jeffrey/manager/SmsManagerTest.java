package com.jeffrey.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmsManagerTest {

    @Autowired
    private SmsManager smsManager;

    @Test
    public void testSendVerifyCode() {
        smsManager.sendVerifyCode("15811400952");
    }

    @Test
    public void testCheckVerifyCode() {
        smsManager.checkVerifyCode("sms:manager:login:15811400952", "1");
    }
}