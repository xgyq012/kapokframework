package com.gdgxwl.base.security;

import com.gdgxwl.base.common.SessionUtil;
import com.gdgxwl.base.service.GxwlSysUserService;
import com.gdgxwl.core.common.json.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public class GxwlFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private GxwlSysUserService userSerivce;

	// 会话超时
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("[" + req.getServletPath() + "] session ID : " + SessionUtil.getSession().getId());
		if (!isLoginRequest(request, response)){
			if (SessionUtil.getCurrentUser() == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("resultCode", -1);
				map.put("resultMsg", "会话超时");
				map.put("errorCode", "SessionTimeout");
				JsonUtil.writeJson(request, response, map);
				return false;
			}
		}
		// 防止重复登录导致的报错，登录前注销一次
		else {
			SecurityUtils.getSubject().logout();
		}
		return super.preHandle(request, response);
	}
	
	// 登录成功
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultCode", 0);
		map.put("resultMsg", "登录成功");
		map.put("errorCode", "");
		map.put("row", subject.getPrincipal());
		JsonUtil.writeJson(request, response, map);
		return false;
//		return super.onLoginSuccess(token, subject, request, response);
	}
	
	// 登录失败
	@Override
	protected boolean onLoginFailure(AuthenticationToken authcToken,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		map.put("resultCode", -1);
		try {
			if (e instanceof UnknownAccountException) {
				map.put("resultMsg", "输入的用户名或密码有误");
				map.put("errorCode", "UnknownAccount");
			}
			else if (e instanceof IncorrectCredentialsException) {
				map.put("resultMsg", "输入的用户名或密码有误");
				map.put("errorCode", "IncorrectCredentials");
			}
			else if (e instanceof DisabledAccountException) {
				map.put("resultMsg", "用户【" + token.getUsername() + "】已经失效");
				map.put("errorCode", "DisabledAccount");
			}
			else {
				map.put("resultMsg", e.getClass().getName());
				map.put("errorCode", e.getClass().getName());
			}
			JsonUtil.writeJson(request, response, map);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
//		return super.onLoginFailure(token, e, request, response);
	}

}
