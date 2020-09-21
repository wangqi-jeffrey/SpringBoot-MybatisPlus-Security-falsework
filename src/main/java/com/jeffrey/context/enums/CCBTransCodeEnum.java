package com.jeffrey.context.enums;

public enum CCBTransCodeEnum {
    SIGN_IN("A3038BDC", "签到"),
    AREA("A3038HMA2", "获取区信息"),
    AREAQUERT("A3038HMA1", "获取小区信息(小区对齐)"),
    PICTURE_UPLOAD("A3038HMM1", "图片上传"),
    WHOLE_RENT("A3038HMH5", "整租房源创建"),
    CHANGE_WHOLE_RENT("A3038HMH6", "整租房源修改"),
    JOINT_RENT("A3038HMH7", "合租房源创建"),
    CHANGE_JOINT_RENT("A3038HMH8", "合租房源修改"),
    CHANGE_JOINT_RENT_ROOM("A3038HMH9", "合租房间修改"),
    HOUSE_UP_DOWN("A3038HMU1", "房源上下架"),
    HOUSE_STATUS("A3038HMS1", "房间状态查询"),
    REGISTER_HOUSE_CALLBACK("A3038HMR8", "注册房源回调"),
    CONTRACT_SIGN("A3038Q101", "合同签约"),
    CONTRACT_CLOSE("A3038Q102", "合同关闭"),
    CONTRACT_QUERY("A3038Q108", "合同查询"),
    BILL_NEW("A3038Q103", "账单新增"),
    BILL_CHANGE("A3038Q104", "账单修改"),
    BILL_DELETE("A3038Q105", "账单删除"),
    BILL_QUERY("A3038Q109", "账单查询"),
    PROJECT_CREATE("A3038HMP1", "门店创建"),
    PROJECT_UPDATE("A3038HMP2", "门店修改"),
    FOCUS_ROOM_TYPE_CREATE("A3038HMR1", "集中式房型创建"),
    FOCUS_UPDATE("A3038HMR2", "集中式房型修改"),
    FOCUS_DELETE("A3038HMR5", "集中式房型删除"),
    FOCUS_ROOM_CREATE("A3038HMH1", "集中式房间创建"),
    FOCUS_ROOM_UPDATE("A3038HMH2", "集中式房间修改"),
    FOCUS_ROOM_DELETE("A3038HMH4", "集中式房间删除"),
    ;

    private String value;
    private String desc;

    private CCBTransCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
