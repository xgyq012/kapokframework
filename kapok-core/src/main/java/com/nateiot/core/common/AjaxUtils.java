package com.nateiot.core.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Will WM. Zhang
 * 
 */
public class AjaxUtils {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

}
