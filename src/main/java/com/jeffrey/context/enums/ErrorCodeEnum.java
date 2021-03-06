package com.jeffrey.context.enums;

/**
 * Description: 响应结果
 * 错误编码说明：错误编码共六位，前两位表示错误级别，中间两位为模块，后两位为错误错误编码
 * 错误级别：10:系统级别，20：服务级别  30:业务级别
 * <p>
 * ClassName: Result
 * date: 2018年11月11日
 *
 * @author YGC
 * @since JDK 1.8
 */
public enum ErrorCodeEnum {

    //----------------------------------------成功
    SUCCESS("0", "success"),

    //----------------------------------------通用错误码
    _10001("081810001", "系统错误！"),
    _10002("081810002", "未知类型错误！"),
    _10003("081810003", "资源未找到！"),
    _10004("081810004", "参数校验失败！"),
    _10005("081810005", "非法请求！"),
    _10006("081810006", "权限校验失败！"),
    _10007("081810007", "服务调用失败！"),
    _10008("081810008", "参数为空！"),
    _10009("081810009", "短信验证码发送失败！"),
    _10010("081810010", "验证码输入有误！"),
    _10011("081810011", "手机号不存在！"),
    _10012("081810012", "账号状态无效！"),
    _10013("081810013", "操作失败，请稍后再试！"),
    _10014("081810014", "释放锁资源失败！"),
    _10015("081810015", "您的令牌缺失，请登录后访问！"),
    _10016("081810016", "登录失败，出现未知异常，请联系管理员！"),
    _10017("081810017", "您的令牌解密失败，请联系管理员！"),
    _10018("081810018", "登录信息已过期，请重新登录！"),
    _10019("081810019", "您的令牌格式错误！"),
    _10020("081810020", "您的令牌没有被正确构造！"),
    _10021("081810021", "您的令牌签名有误！"),
    _10022("081810022", "您的令牌存在非法参数，请勿伪造令牌！"),
    _10023("081810023", "您的令牌解密失败，请联系管理员！"),
    _10024("081810024", "获取用户信息失败，请联系管理员！"),
    _10025("081810025", "该手机号已被注册为房东账号！"),
    _10026("081810026", "该手机号已被注册为管家账号！"),
    _10027("081810027", "验证用户身份失败，请重新登录！"),
    _10028("081810028", "手机号不存在！"),
    _10029("081810029", "字典不存在！"),

    //----------------------------------------房源模块 20001~20300
    _20001("081820001", "房源不存在!"),
    _20002("081820002", "房源价格不存在!"),
    _20003("081820003", "您还有X间房未配置房型，请先配置房型"),
    _20004("081820004", "请联系客服增加可用房源"),


    //----------------------------------------房型模块 20301~20600
    _20301("081820301", "房型不存在！"),
    _20302("081820302", "房型下有关联房间，不能删除！"),

    //----------------------------------------房间模块 20601~20900
    _60101("081860101", "房间不存在!"),
    _60102("081860102", "抄表记录不存在!"),
    _60103("081860103", "本次读数不得小于上次读数!"),
    _60104("081860104", "抄表记录已结算不能修改!"),
    _60105("081860105", "抄表月份不能大于当前月份!"),


    //----------------------------------------出租合同模块 30001~40000
    _30001("081830001", "出租合同不存在！"),
    _30002("081830002", "当前房间存在出租合同！"),
    _30003("081830003", "租客已确认，合同不允许编辑！"),
    _30004("081830004", "租客不存在！"),
    _30005("081830005", "出租合同已退租，不可重复退租！"),
    _30006("081830006", "只有退租未结算的合同才可以操作撤销退租！"),
    _30007("081830007", "出租合同其他费用不存在！"),
    _30008("081830008", "合同已确认，无需重复确认！"),
    _30009("081830009", "合同已确认，不允许编辑！"),
    _30010("081830010", "合同已确认，不允许删除！"),
    _30011("081830011", "%s表读数不得小于上次读数！"),

    //----------------------------------------账单支付模块 40001~50000
    _40001("081840001", "账单不存在！"),
    _40002("081840002", "账单明细不存在！"),
    _40003("081840003", "账单明细不允许删除！"),
    _40004("081840004", "账单状态不允许改期！"),
    _40005("081840005", "账单应收款日只能改为明天以后！"),
    _40006("081840006", "账单应收款日必须处于签约周期内！"),
    _40007("081840007", "不允许添加账单明细！"),
    _40008("081840008", "押金不允许添加周期费用！"),
    _40009("081840009", "未找到可以添加账单明细的租金账单！"),
    _40010("081840010", "账单状态不允许拆分！"),
    _40011("081840011", "账单拆分金额错误！"),
    _40012("081840012", "账单已支付，请勿重复操作！"),
    _40013("081840013", "金额不足以支付滞纳金！"),
    _40014("081840014", "存在滞纳金的账单不允许拆分！"),
    _40015("081840015", "账单金额错误！"),
    _40016("081840016", "调起收银台失败！"),
    _40017("081840017", "租约未确认不允许查看账单详细信息！"),
    _40018("081840018", "支付金额有误，请核实后操作！"),
    _40019("081840019", "未查询到待支付账单，请核实后操作！"),
    _40020("081840020", "账单流水不存在！"),
    _40021("081840021", "账单流水明细不存在！"),
    _40022("081840022", "当前账单均已进行催缴"),


    //----------------------------------------文件模块 50001~60000
    _50001("081850001", "上传文件为空"),
    _50002("081850002", "上传文件超过限制"),
    _50003("081850003", "上传文件失败"),
    _50004("081850004", "上传文件数量超出限制"),
    ;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    ErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static String code(String name) {
        return ErrorCodeEnum.valueOf(name).errorCode;
    }

    public static String msg(String name) {
        return ErrorCodeEnum.valueOf(name).errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}