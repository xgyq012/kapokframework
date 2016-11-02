package com.gdgxwl.base.repository;

import com.gdgxwl.base.domain.GxwlSysUser;
import com.gdgxwl.core.repository.BaseDao;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysUserDao extends BaseDao<GxwlSysUser, Integer>,
		GxwlSysUserDaoPlus {

	// public GxwlSysUser findByEmpcode(String empcode);

	public GxwlSysUser findByUserName(String userName);

	public GxwlSysUser findByUserId(Integer userId);
}
