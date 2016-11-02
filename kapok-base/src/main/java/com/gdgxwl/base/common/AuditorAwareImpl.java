package com.gdgxwl.base.common;

import com.gdgxwl.base.domain.GxwlSysUser;
import com.gdgxwl.base.security.GxwlShiroUser;
import org.springframework.data.domain.AuditorAware;

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
