package com.jeffrey.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 正则表达式工具类
 *
 * @author WQ
 * @date 2020/8/25 2:49 PM
 */
public class RegexUtil {

    // 中文 + 中英文括弧
    public static final String CN_BRACKETS_REG = "^([\\u4E00-\\u9FA5]|||\\（|\\）|\\(|\\)){0,}$";
    // 中文
    public static final String CN_REG = "^([\\u4E00-\\u9FA5]){1,}$";
    // 数字
    public static final String NUM_REG = "[0-9]*";
    // 手机号
    public static final String PHONE_REG = "^1((3[0-9])|(4[0-9])|(5[0-9])|(6[0-9])|(7[0-9])|(8[0-9])|(9[0-9]))\\d{8}$";
    // 密码
    public static final String PASSWORD_REG = "^.{6,}$";

    /**
     * 公司名称：中英文、数字、下划线，括弧组合
     * ^  与字符串开始的地方匹配
     * (?!_) 不能以_开头
     * (?!.*?_$) 不能以_结尾
     * [a-zA-Z0-9_\u4e00-\u9fa5]+ 至少一个汉字、数字、字母、下划线
     */
    public static final String COMPANY_NAME_REG = "^((?!_)(\\（|\\）|\\(|\\)){0,}[a-zA-Z0-9_\\u4e00-\\u9fa5]){1,100}$";

    /**
     * 品牌名称：支持中文、英文、数字以及"_"，长度为4-15个字符；
     */
    public static final String BRAND_NAME_REG = "^(?!_)[a-zA-Z0-9_\\u4e00-\\u9fa5]{4,15}$";

    /**
     * 姓名
     */
    public static final String NAME_REG = "^[\\u4e00-\\u9fa5]{2,20}";

    /**
     * 公司名称
     */
    public static final String COMPANYNAME_REG = "^[\\u4e00-\\u9fa5]{2,30}";


    public static boolean match(String regexp, String srcStr) {
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(srcStr);
        return m.matches();
    }

}
