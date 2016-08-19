package com.nateiot.base.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.core.repository.BaseDaoPlus;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysUserDaoPlus extends BaseDaoPlus {

	public List<Map<String, Object>> getUserPermissionList(Integer userId);
	
}
