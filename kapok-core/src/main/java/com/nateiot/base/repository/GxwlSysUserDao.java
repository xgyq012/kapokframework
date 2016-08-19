package com.nateiot.base.repository;

import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.core.repository.BaseDao;

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
