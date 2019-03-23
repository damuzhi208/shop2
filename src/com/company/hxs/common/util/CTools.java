package com.company.hxs.common.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.StringUtils;

public class CTools {
	
	/**
	 * 判断对象为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			return !StringUtils.isEmpty(obj);
		}
		if (obj instanceof Collection && ((Collection<?>) obj).isEmpty()) {
			return false;
		}
		if (obj instanceof Map && ((Map<?, ?>) obj).isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 将字符串大小写互换
	 * 
	 * @param str
	 */
	public static String isUpAndLow(String str) {
		char[] chars = str.toCharArray();
		for (int i = 0, length = chars.length; i < length; i++) {
			char c = chars[i];
			// 判断字母是不是大写，如果是大写变为小写
			if (Character.isUpperCase(c)) {
				chars[i] = Character.toLowerCase(c);
				continue;
			}
			// 如果为小写，变为大写
			chars[i] = Character.toUpperCase(c);
		}
		String str1 = new String(chars);
		System.err.println(str1);
		return str1;
	}

	/**
	 * 判断字符串是否为大写
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isUpperAll(String str) {
		boolean flag = true;
		char[] chars = str.toCharArray();
		for (int i = 0, length = chars.length; i < length; i++) {
			char c = chars[i];
			// 判断字母是不是大写，如果是大写变为小写
			if (!Character.isUpperCase(c)) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
