package com.gdgxwl.base.common;

import com.gdgxwl.base.security.GxwlShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @author Will WM. Zhang
 *
 */
public class SessionUtil {

	
	/**
	 * 获取当前的Session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 给当前Session设值
	 * 
	 * @param key
	 * @param value
	 */
	public static void setAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	/**
	 * 从当前Session取值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 从当前Session删除值
	 * 
	 * @param key
	 */
	public static void removeAttribute(Object key) {
		getSession().removeAttribute(key);
	}

	/**
	 * 从当前Session中获取Principal（当事人）对象
	 * 
	 * @return
	 */
	public static GxwlShiroUser getCurrentUser() {
		return (GxwlShiroUser)SecurityUtils.getSubject().getPrincipal();
	}
	
}
