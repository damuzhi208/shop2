package com.company.hxs.common.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.StringUtils;

public class CTools {
	
	/**
	 * �ж϶���Ϊ��
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
	 * ���ַ�����Сд����
	 * 
	 * @param str
	 */
	public static String isUpAndLow(String str) {
		char[] chars = str.toCharArray();
		for (int i = 0, length = chars.length; i < length; i++) {
			char c = chars[i];
			// �ж���ĸ�ǲ��Ǵ�д������Ǵ�д��ΪСд
			if (Character.isUpperCase(c)) {
				chars[i] = Character.toLowerCase(c);
				continue;
			}
			// ���ΪСд����Ϊ��д
			chars[i] = Character.toUpperCase(c);
		}
		String str1 = new String(chars);
		System.err.println(str1);
		return str1;
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ��д
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isUpperAll(String str) {
		boolean flag = true;
		char[] chars = str.toCharArray();
		for (int i = 0, length = chars.length; i < length; i++) {
			char c = chars[i];
			// �ж���ĸ�ǲ��Ǵ�д������Ǵ�д��ΪСд
			if (!Character.isUpperCase(c)) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
