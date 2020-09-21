package com.jeffrey.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 字符串处理工具类
 *
 * @author 滕国栋
 * @date 2020/08/17 下午 21:49
 */
public class StringUtil extends StringUtils {

	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final String TAB = "	";

	public static final String ACUTE = "`";
	public static final String TILDE = "~";
	public static final String EXCLAMATION = "!";
	public static final String AT = "@";
	public static final String NUMBER = "#";
	public static final String DOLLAR = "$";
	public static final String PERCENT = "%";
	public static final String CARET = "^";
	public static final String AND = "&";
	public static final String STAR = "*";
	public static final String LEFT_PARENTHESIS = "(";
	public static final String RIGHT_PARENTHESIS = ")";
	public static final String HYPHEN = "-";
	public static final String UNDERSCORE = "_";
	public static final String EQUALS = "=";
	public static final String PLUS = "+";

	public static final String LEFT_SQUARE_BRACKET = "[";
	public static final String RIGHT_SQUARE_BRACKET = "]";
	public static final String LEFT_CURLY_BRACE = "{";
	public static final String RIGHT_CURLY_BRACE = "}";
	public static final String BACKSLASH = "\\";
	public static final String VERTICAL_BAR = "|";
	public static final String SEMI_COLON = ";";
	public static final String COLON = ":";
	public static final String SINGLE_QUOTATION = "'";
	public static final String DOUBLE_QUOTATION = "\"";
	public static final String COMMA = ",";
	public static final String DOT = ".";
	public static final String DOT2 = "·";
	public static final String DOT3 = "。";
	public static final String LEFT_ANGLE_BRACKET = "<";
	public static final String RIGHT_ANGLE_BRACKET = ">";
	public static final String SLASH = "/";
	public static final String QUESTION = "?";

	public static String unicodeToString(String str) {

		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			// group 6728
			String group = matcher.group(2);
			// ch:'木' 26408
			ch = (char) Integer.parseInt(group, 16);
			// group1 \u6728
			String group1 = matcher.group(1);
			str = str.replace(group1, ch + "");
		}
		return str;
	}

	public static Double parseDouble(String doubleStr) {
		try {
			return Double.parseDouble(doubleStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Float parseFloat(String floatStr) {
		try {
			return Float.parseFloat(floatStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Long parseLong(String longStr) {
		try {
			return Long.parseLong(longStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Integer parseInt(String intStr) {
		try {
			return Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 将string转为long类型，若string为null、空，或者无法解析为数字，则返回给定的默认值
	 *
	 * @param longStr
	 * @param defaultValue
	 * @return
	 */
	public static long parseLong(String longStr, long defaultValue) {
		try {
			return Long.parseLong(longStr);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 将string转为int类型，若string为null、空，或者无法解析为数字，则返回给定的默认值
	 *
	 * @param intStr
	 * @param defaultValue
	 * @return
	 */
	public static int parseInt(String intStr, int defaultValue) {
		try {
			return Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * redis值是否为空
	 *
	 * @param cs
	 * @return
	 */
	public static boolean isRedisBlank(String cs) {
		if (isBlank(cs) || "null".equals(cs.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 根据指定的正则表达式，对字符串进行校验
	 *
	 * @param cs
	 * @param regex
	 * @return
	 */
	public static boolean verifyString(String cs, String regex) throws NullPointerException {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cs);
		return matcher.matches();
	}

	/**
	 * 判断两个字符串的值是否相等，包括null
	 *
	 * @return
	 */
	public static boolean isStringEquals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

	/**
	 * SQL查询 参数拼接（List转String）
	 *
	 * @return
	 */
	public static String changeIntegerListToSqlStringNotEmpty(List<Integer> list) {
		if (CollectionUtils.isEmpty(list)) {
			return "''";
		}
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < list.size(); i++) {
			if (sb.length() == 0) {
				sb.append("'" + list.get(i) + "'");
			} else {
				sb.append(",'" + list.get(i) + "'");
			}
		}
		if (StringUtils.isBlank(sb.toString())) {
			return "''";
		}
		return sb.toString();
	}

	// 判断是否包含特殊字符
	public static boolean isContainsSpecial(String str) {
		// 只允许字母和数字和英文逗号 // String regEx = "[^a-zA-Z0-9]";
		String regex = "[`~!@#$%^&*()+=|{}':;'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】《》‘；：”“’。，、？]";
		Matcher m = Pattern.compile(regex).matcher(str);
		return m.find();
	}

	/**
	 * 判断是否含有字符
	 *
	 * @return
	 */
	public static boolean isContainsStr(String str) {
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = Pattern.compile(regex).matcher(str);
		return m.matches();
	}

	/**
	 * 判断是否含有emoji表情
	 *
	 * @param str
	 * @return
	 */
	public static boolean hasEmoji(String str) {
		Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断传进来的字符串，是否 大于指定的字节，如果大于递归调用 直到小于指定字节数 ，一定要指定字符编码，因为各个系统字符编码都不一样，字节数也不一样
	 *
	 * @param s
	 *            原始字符串
	 * @param num
	 *            传进来指定字节数
	 * @return String 截取后的字符串
	 *
	 */
	public static String idgui(String s, int num) {
		try {
			if (StringUtil.isBlank(s)) {
				return s;
			}
			int changdu = s.getBytes("UTF-8").length;
			if (changdu > num) {
				s = s.substring(0, s.length() - 1);
				s = idgui(s, num);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return s;
	}

	/**
	 * 获取uuid字符串
	 *
	 * @return
	 */
	public static String generateUUIDStr() {
		String uuid = UUID.randomUUID().toString();

		// 去掉“-”符号
		uuid = uuid.replace("-", "");

		return uuid;
	}

	public static String generate32UUIDStr(String prefix) {
		String uuid = generateUUIDStr().toUpperCase();
		if (isBlank(prefix))
			return uuid;

		uuid = prefix + uuid;
		return uuid.substring(0, uuid.length() - prefix.length());
	}

	public static String generateUserCode(String prefix) {
		return prefix + generateUUIDStr().toUpperCase();
	}
}
