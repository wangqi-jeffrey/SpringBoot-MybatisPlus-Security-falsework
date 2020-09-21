/**
 * Project Name: 26.02.06.XX
 * File Name: MoneyUtil.java
 * Package Name: com.huifenqi.saas.common
 * Date: 2016年12月21日下午12:40:14
 * Copyright (c) 2016, www.huizhaofang.com All Rights Reserved.
 */
package com.jeffrey.utils;

import java.math.BigDecimal;

/**
 * Description: 金额处理工具类
 *
 * @author 滕国栋
 * @date 2020/08/17 下午 21:49
 */
public class MoneyUtil {
    /**
     * 金额为元时，精度保留到小数点后2位
     */
    private static final int MONEY_SCALE_IN_YUAN = 2;

    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] RADICES = {"", "拾", "佰", "仟"};
    private static final String[] BIG_RADICES = {"", "万", "亿", "兆"};

    /**
     * 获取以元表示的金额字符串
     *
     * @param moneyInCent
     * @return
     */
    public static String getMoneyDescInYuan(Integer moneyInCent) {
        if (moneyInCent == null) {
            return null;
        } else {
            return BigDecimal.valueOf(moneyInCent, MONEY_SCALE_IN_YUAN).toPlainString();
        }
    }

    /**
     * 获取以元表示的金额字符串
     *
     * @param moneyInCent
     * @return
     */
    public static String getMoneyDescInYuan(Long moneyInCent) {
        if (moneyInCent == null) {
            return null;
        } else {
            return BigDecimal.valueOf(moneyInCent, MONEY_SCALE_IN_YUAN).toPlainString();
        }
    }

    /**
     * 四舍五入取元
     *
     * @param moneyInCent
     * @return
     */
    public static String getMoneyDescYuan(Integer moneyInCent) {
        if (moneyInCent == null) {
            return null;
        } else {
            return BigDecimal.valueOf(moneyInCent, MONEY_SCALE_IN_YUAN)
                    .setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
    }

    /**
     * 获取调整后的以元表示的金额字符串(去掉小数点后多余的0)
     *
     * @param moneyInCent
     * @return
     */
    public static String getTrimmedMoneyDescInYuan(Integer moneyInCent) {

        String trimmedMoneyDescInYuan = getMoneyDescInYuan(moneyInCent);
        if (trimmedMoneyDescInYuan != null) {
            if (trimmedMoneyDescInYuan.indexOf(".") > 0) {
                trimmedMoneyDescInYuan = trimmedMoneyDescInYuan.replaceAll("0+?$", "");
                trimmedMoneyDescInYuan = trimmedMoneyDescInYuan.replaceAll("[.]$", "");
            }
        }
        return trimmedMoneyDescInYuan;
    }

    /**
     * 获取调整后的以元表示的金额字符串(去掉小数点后多余的0)
     *
     * @param moneyInCent
     * @return
     */
    public static String getTrimmedMoneyDescInYuan(Long moneyInCent) {

        String trimmedMoneyDescInYuan = getMoneyDescInYuan(moneyInCent);
        if (trimmedMoneyDescInYuan != null) {
            if (trimmedMoneyDescInYuan.indexOf(".") > 0) {
                trimmedMoneyDescInYuan = trimmedMoneyDescInYuan.replaceAll("0+?$", "");
                trimmedMoneyDescInYuan = trimmedMoneyDescInYuan.replaceAll("[.]$", "");
            }
        }
        return trimmedMoneyDescInYuan;
    }

    /**
     * 获取以分表示的金额数值
     *
     * @param moneyDescInYuan
     * @return
     */
    public static long getMoneyInCent(String moneyDescInYuan) {
        BigDecimal amount = new BigDecimal(moneyDescInYuan);
        BigDecimal movePointRight = amount.movePointRight(MONEY_SCALE_IN_YUAN);
        long cent = movePointRight.setScale(0, BigDecimal.ROUND_HALF_EVEN).longValue();
        return cent;
    }

    /**
     * 获取大写的人名币的金额，单位精确到分
     *
     * @param money 人民币，单位：分
     * @return 人民币大写的金额
     */
    public static String getRMB(long money) {
        StringBuilder result = new StringBuilder();
        if (money == 0) {
            return "零元整";
        }
        long integral = money / 100;//整数部分
        int integralLen = (integral + "").length();
        int decimal = (int) (money % 100);//小数部分
        if (integral > 0) {
            int zeroCount = 0;
            for (int i = 0; i < integralLen; i++) {
                int unitLen = integralLen - i - 1;
                int d = Integer.parseInt((integral + "").substring(i, i + 1));//当前数字的值
                int quotient = unitLen / 4;//大单位的下标{"", "万", "亿"}
                int modulus = unitLen % 4;//获取单位的下标（整数部分都是以4个数字一个大单位，比如：个、十、百、千、个万、十万、百万、千万、个亿、十亿、百亿、千亿）
                if (d == 0) {
                    zeroCount++;
                } else {
                    if (zeroCount > 0) {
                        result.append(CN_UPPER_NUMBER[0]);
                    }
                    zeroCount = 0;
                    result.append(CN_UPPER_NUMBER[d]).append(RADICES[modulus]);
                }
                if (modulus == 0 && zeroCount < 4) {
                    result.append(BIG_RADICES[quotient]);
                }
            }
            result.append("元");
        }
        if (decimal > 0) {
            int j = decimal / 10;
            if (j > 0) {
                result.append(CN_UPPER_NUMBER[j]).append("角");
            }
            j = decimal % 10;
            if (j > 0) {
                result.append(CN_UPPER_NUMBER[j]).append("分");
            }
        } else {
            result.append("整");
        }
        return result.toString();
    }

    /**
     * 元转分，确保price保留两位有效数字
     * @return
     */
    public static Long changeY2F(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(100)).longValue();
    }

    /**
     * 分转元，转换为bigDecimal在toString
     * @return
     */
    public static BigDecimal changeF2Y(Long price) {
        return BigDecimal.valueOf(price).divide(new BigDecimal(100));
    }
}
