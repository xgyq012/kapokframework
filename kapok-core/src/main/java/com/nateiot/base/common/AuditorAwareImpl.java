package com.nateiot.base.common;

import org.springframework.data.domain.AuditorAware;

import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.security.GxwlShiroUser;

/**
 * @author Will WM. Zhang
 * 
 */
public class AuditorAwareImpl implements AuditorAware<GxwlSysUser> {

	@Override
	public GxwlSysUser getCurrentAuditor() {
		GxwlSysUser gxwlSysUser = new GxwlSysUser();
		GxwlShiroUser shiroUser = SessionUtil.getCurrentUser();
		if (shiroUser != null) {
			gxwlSysUser.setUserId(shiroUser.getUserId());
			gxwlSysUser.setRealname(shiroUser.getRealname());
		} else {
			gxwlSysUser.setUserId(1);
			gxwlSysUser.setRealname("超级用户");
		}
		return gxwlSysUser;
	}

}
