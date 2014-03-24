package com.rbac.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);

	}

	public String[] getParameterValues(String parameter) {
		String[] results = super.getParameterValues(parameter);
		String[] trimResults = null;
		if (results == null)
			return null;
		else {
			int count = results.length;
			trimResults = new String[count];
			for (int i = 0; i < count; i++) {
				trimResults[i] = this.ToDBC(results[i]);
			}
		}
		return trimResults;
	}

	/**
	 * 全半角转换
	 * @param input
	 * @return
	 */
	private String ToDBC(String input) {
		if(StringUtils.isNotBlank(input)){
			char c[] = input.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == '\u3000') {
					c[i] = ' ';
				} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
					c[i] = (char) (c[i] - 65248);
	
				}
			}
			return new String(c);
		}
		return input;
	}
}
