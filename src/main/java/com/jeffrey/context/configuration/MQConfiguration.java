package com.jeffrey.context.configuration;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 消息队列配置
 *
 * @author TGD
 * @date 2020/08/26 下午 16:33
 */
public class MQConfiguration {
    public static final String QUEUE_PREFIX = "queue:";

    //-----------------------队列-------------------
    //-----------------------房源
    public static final String QUEUE_HOUSE_ADD_RENT_CONTRACT = "house:add:rent:contract";
    public static final String QUEUE_HOUSE_UPDATE_RENT_CONTRACT = "house:update:rent:contract";
    public static final String QUEUE_HOUSE_CHECKOUT_RENT_CONTRACT = "house:checkout:rent:contract";
    public static final String QUEUE_HOUSE_DELETE_RENT_CONTRACT = "house:delete:rent:contract";

    //-----------------------出租合同

    //-----------------------账单
    public static final String QUEUE_BILL_ADD_RENT_CONTRACT = "bill:add:rent:contract";
    public static final String QUEUE_BILL_UPDATE_RENT_CONTRACT = "bill:update:rent:contract";
    public static final String QUEUE_BILL_CHECKOUT_RENT_CONTRACT = "bill:checkout:rent:contract";
    public static final String QUEUE_BILL_DELETE_RENT_CONTRACT = "bill:delete:rent:contract";
    public static final String QUEUE_BILL_COLLECT = "bill:collect";
    public static final String QUEUE_BILL_ITEM_CHANGE = "bill:item:change";

    //-----------------------建融
    public static final String QUEUE_CCB_ADD_HOUSE = "ccb:add:house";
    public static final String QUEUE_CCB_UPDATE_HOUSE = "ccb:update:house";
    public static final String QUEUE_CCB_CONFIRM_RENT_CONTRACT = "ccb:confirm:rent:contract";
    public static final String QUEUE_CCB_CHECKOUT_RENT_CONTRACT = "ccb:checkout:rent:contract";
    public static final String QUEUE_CCB_BIND_ROOM_TYPE = "ccb:bind:room:type";

    //-----------------------日志
    public static final String QUEUE_USER_OPERATE_LOG = "user:operate:log";

    //-----------------------主题队列-------------------
    public static final String TOPIC_ADD_HOUSE = "TOPIC_ADD_HOUSE";
    public static final String TOPIC_UPDATE_HOUSE = "TOPIC_UPDATE_HOUSE";
    public static final String TOPIC_BIND_ROOM_TYPE = "TOPIC_BIND_ROOM_TYPE";
    public static final String TOPIC_ADD_RENT_CONTRACT = "TOPIC_ADD_RENT_CONTRACT";
    public static final String TOPIC_UPDATE_RENT_CONTRACT = "TOPIC_UPDATE_RENT_CONTRACT";
    public static final String TOPIC_CHECKOUT_RENT_CONTRACT = "TOPIC_CHECKOUT_RENT_CONTRACT";
    public static final String TOPIC_DELETE_RENT_CONTRACT = "TOPIC_DELETE_RENT_CONTRACT";
    public static final String TOPIC_CONFIRM_RENT_CONTRACT = "TOPIC_CONFIRM_RENT_CONTRACT";

    public static final Map<String, List<String>> TOPIC_MAP = new HashMap<>();

    static {
        TOPIC_MAP.put(TOPIC_ADD_HOUSE, Lists.newArrayList(QUEUE_CCB_ADD_HOUSE));
        TOPIC_MAP.put(TOPIC_UPDATE_HOUSE, Lists.newArrayList(QUEUE_CCB_UPDATE_HOUSE));
        TOPIC_MAP.put(TOPIC_BIND_ROOM_TYPE, Lists.newArrayList(QUEUE_CCB_BIND_ROOM_TYPE));
        TOPIC_MAP.put(TOPIC_ADD_RENT_CONTRACT, Lists.newArrayList(QUEUE_HOUSE_ADD_RENT_CONTRACT, QUEUE_BILL_ADD_RENT_CONTRACT));
        TOPIC_MAP.put(TOPIC_UPDATE_RENT_CONTRACT, Lists.newArrayList(QUEUE_HOUSE_UPDATE_RENT_CONTRACT, QUEUE_BILL_UPDATE_RENT_CONTRACT));
        TOPIC_MAP.put(TOPIC_CHECKOUT_RENT_CONTRACT, Lists.newArrayList(QUEUE_HOUSE_CHECKOUT_RENT_CONTRACT, QUEUE_BILL_CHECKOUT_RENT_CONTRACT, QUEUE_CCB_CHECKOUT_RENT_CONTRACT));
        TOPIC_MAP.put(TOPIC_DELETE_RENT_CONTRACT, Lists.newArrayList(QUEUE_HOUSE_DELETE_RENT_CONTRACT, QUEUE_BILL_DELETE_RENT_CONTRACT));
        TOPIC_MAP.put(TOPIC_CONFIRM_RENT_CONTRACT, Lists.newArrayList(QUEUE_CCB_CONFIRM_RENT_CONTRACT));
    }
}
