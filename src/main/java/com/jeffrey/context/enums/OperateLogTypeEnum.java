package com.jeffrey.context.enums;

public enum OperateLogTypeEnum {

    // --------------------房源类--------------------
    HOUSE_ADD(Module.HOUSE, "新建房源"),
    HOUSE_UPDATE(Module.HOUSE, "修改房源"),

    // --------------------户型--------------------
    ROOM_TYPE_ADD(Module.HOUSE, "新增户型"),
    ROOM_TYPE_UPDATE(Module.HOUSE, "修改户型"),
    ROOM_TYPE_DELETE(Module.HOUSE, "删除户型"),

    // --------------------房间--------------------
    ROOM_TYPE_BIND(Module.HOUSE, "房间绑定户型"),
    ROOM_METER_READING(Module.HOUSE, "房间抄表"),

    // --------------------租约类--------------------
    RENT_CONTRACT_ADD(Module.RENT_CONTRACT, "新建租约"),
    RENT_CONTRACT_UPDATE(Module.RENT_CONTRACT, "编辑租约"),
    RENT_CONTRACT_DELETE(Module.RENT_CONTRACT, "删除租约"),
    RENT_CONTRACT_CONFIRM(Module.RENT_CONTRACT, "确认租约"),
    RENT_CONTRACT_CHECKOUT(Module.RENT_CONTRACT, "退租租约"),
    RENT_CONTRACT_CANCEL_CHECKOUT(Module.RENT_CONTRACT, "取消退租租约"),
    RENT_CONTRACT_TENANT_ADD(Module.RENT_CONTRACT, "添加租客"),
    RENT_CONTRACT_TENANT_UPDATE(Module.RENT_CONTRACT, "编辑租客"),
    RENT_CONTRACT_TENANT_DELETE(Module.RENT_CONTRACT, "删除租客"),


    // --------------------账单类--------------------
    BILL_ADD(Module.BILL, "添加账单"),
    BILL_DELETE(Module.BILL, "删除账单"),
    BILL_UPDATE(Module.BILL, "编辑账单"),
    BILL_COLLECTION_DATE_UPDATE(Module.BILL, "账单改期"),
    BILL_SPLIT(Module.BILL, "账单拆分"),
    BILL_COLLECTION(Module.BILL, "账单收款"),

    // --------------------收款类--------------------


    // --------------------用户类--------------------
    USER_BUSINESS_LOGIN(Module.USER_BUSINESS, "B端用户登录"),
    USER_BUSINESS_LOGOUT(Module.USER_BUSINESS, "B端用户退出登录"),
    USER_CUSTOMER_LOGIN(Module.USER_CUSTOMER, "C端用户登录"),
    USER_CUSTOMER_LOGOUT(Module.USER_CUSTOMER, "C端用户退出登录"),

    ;
    private Module module;

    private String operateTypeName;

    OperateLogTypeEnum(Module module, String operateTypeName) {
        this.module = module;
        this.operateTypeName = operateTypeName;
    }

    public boolean isInModule(Module module) {
        return this.module == module;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getOperateTypeName() {
        return operateTypeName;
    }

    public void setOperateTypeName(String operateTypeName) {
        this.operateTypeName = operateTypeName;
    }

    public enum Module {
        HOUSE("房源类"),
        RENT_CONTRACT("租约类"),
        BILL("账单类"),
        PAYMENT("收款类"),
        USER_BUSINESS("B端用户"),
        USER_CUSTOMER("C端用户"),
        ;

        private String name;

        Module(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}