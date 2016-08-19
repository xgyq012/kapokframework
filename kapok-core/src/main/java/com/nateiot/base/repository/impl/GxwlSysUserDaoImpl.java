package com.nateiot.base.repository.impl;

import java.util.List;
import java.util.Map;

import com.nateiot.base.repository.GxwlSysUserDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 * @author Will WM. Zhang
 * 
 */
public class GxwlSysUserDaoImpl extends BaseDaoImpl implements
		GxwlSysUserDaoPlus {

	@Override
	public List<Map<String, Object>> getUserPermissionList(Integer userId) {
		String sqlString = " SELECT"
								+ " v.RESOURCE_CODE as resourceCode"
							+ " FROM"
								+ " v_gxwl_sys_userresource v"
							+ " WHERE"
								+ " v.USER_ID = " + userId;
		return selectBySql(sqlString);
	}
	
}
