package com.rbac.util;

import org.apache.commons.lang.StringUtils;

public class CommonUtils {

	/**
	 * 判断字符串不为null或空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return StringUtils.isNotEmpty(str);
	}
	
	/**
	 * 判断字符串为null或空字符串
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * 判断字符串不为null或空字符串或空白
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str){
		return StringUtils.isNotBlank(str);
	}
	
	/**
	 * 判断字符串为null或空字符串或空白
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		return StringUtils.isBlank(str);
	}
	
	/**
	 * 字符串转长整形，转换失败时返回null
	 * @param str
	 * @return
	 */
	public static Long parseLong(String str){
		try{
			return Long.parseLong(str);
		}
		catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 字符串转整形，转换失败时返回null
	 * @param str
	 * @return
	 */
	public static Integer parseInteger(String str){
		try{
			return Integer.parseInt(str);
		}
		catch(Exception e){
			return null;
		}
	}
}
