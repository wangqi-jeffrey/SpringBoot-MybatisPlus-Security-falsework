package com.jeffrey.manager;

import com.jeffrey.vo.wechat.CollectionMsgVO;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class WxMaManagerTest {

    @Autowired
    private WxMaManager wxMaManager;

    @Test
    void sendCollectionMsg() {
        CollectionMsgVO collectionMsgVO = new CollectionMsgVO();
        collectionMsgVO.setCollectionDate(new Date());
        collectionMsgVO.setAmount(108936L);
        collectionMsgVO.setPhone("15811400952");
        try {
            wxMaManager.sendCollectionMsg(collectionMsgVO);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}