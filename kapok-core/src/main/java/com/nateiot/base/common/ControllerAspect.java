package com.nateiot.base.common;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.nateiot.base.domain.GxwlSysOperatelog;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.security.GxwlShiroUser;
import com.nateiot.base.service.GxwlSysOperatelogService;

/**
 * @author Will WM. Zhang
 * 
 */
@Aspect
public class ControllerAspect {

	@Autowired
	private GxwlSysOperatelogService logService;

	@After("execution(* com.nateiot.base.web.*.*(..)) && !target(com.nateiot.base.web.GxwlSysOperatelogController)")
	public void addLog(JoinPoint point) {
		GxwlShiroUser currentUser = SessionUtil.getCurrentUser();
		if (currentUser != null) {
			GxwlSysUser operator = new GxwlSysUser();
			operator.setUserId(currentUser.getUserId());

			Signature signature = point.getSignature();
			String className = StringUtils.substringAfterLast(
					signature.getDeclaringTypeName(), ".");
			String methodName = signature.getName();

			GxwlSysOperatelog gxwlSysOperatelog = new GxwlSysOperatelog();

			gxwlSysOperatelog.setOperateType(getOperateType(methodName));
			gxwlSysOperatelog.setOperator(operator);
			gxwlSysOperatelog.setLogDesc(className + "." + methodName);
			gxwlSysOperatelog.setOperateTime(new Date());

			logService.doSave(gxwlSysOperatelog);
		}
	}

	private String getOperateType(String methodName) {
		if (StringUtils.containsIgnoreCase(methodName, "index")) {
			return "登录";
		}
		if (StringUtils.containsIgnoreCase(methodName, "logout")) {
			return "退出";
		}
		if (StringUtils.containsIgnoreCase(methodName, "search")) {
			return "查询";
		}
		if (StringUtils.containsIgnoreCase(methodName, "save")) {
			return "保存";
		}
		if (StringUtils.containsIgnoreCase(methodName, "del")) {
			return "删除";
		}
		if (StringUtils.containsIgnoreCase(methodName, "import")) {
			return "导入";
		}
		if (StringUtils.containsIgnoreCase(methodName, "export")) {
			return "导出";
		}
		if (StringUtils.containsIgnoreCase(methodName, "print")) {
			return "打印";
		}
		return "其他";
	}

}
