package com.rbac.util;

import org.apache.commons.lang.StringUtils;

public class CommonUtils {

	public static boolean isNotEmpty(String str){
		return StringUtils.isNotEmpty(str);
	}
	
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	
	public static boolean isNotBlank(String str){
		return StringUtils.isNotBlank(str);
	}
	
	public static boolean isBlank(String str){
		return StringUtils.isBlank(str);
	}
	
	public static Long parseLong(String str){
		try{
			return Long.parseLong(str);
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static Integer parseInteger(String str){
		try{
			return Integer.parseInt(str);
		}
		catch(Exception e){
			return null;
		}
	}
}
