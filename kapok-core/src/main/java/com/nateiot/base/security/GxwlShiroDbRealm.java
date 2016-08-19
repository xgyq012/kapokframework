package com.nateiot.base.security;


import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.service.GxwlSysUserService;
import com.nateiot.core.common.security.HashUtil;

/**
 * @author Will WM. Zhang
 * 
 */
public class GxwlShiroDbRealm extends AuthorizingRealm {
	
	@Autowired
	private GxwlSysUserService userService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		GxwlSysUser user = userService.findByUserName(token.getUsername());
		if (user == null) {
			throw new UnknownAccountException(); // 没找到账号
		}
		if ("disabled".equals(user.getStatus())) {
			throw new DisabledAccountException(); // 无效的账号
		}
		String username = user.getUserName();
		String salt = user.getSalt();
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				new GxwlShiroUser(user.getUserId(), user.getUserName(), user.getRealname()), 
				user.getPassword(),
				ByteSource.Util.bytes(username + salt),
				getName());
		return simpleAuthenticationInfo;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		GxwlShiroUser user = (GxwlShiroUser)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(userService.getUserPermissionList(user.getUserId()));
		return info;
	}
	
	/**
	 * 设定密码校验算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HashUtil.SHA256);
		matcher.setHashIterations(GxwlSysUserService.DEFAULT_HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
}
