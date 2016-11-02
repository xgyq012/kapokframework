package com.gdgxwl.base.repository;

import com.gdgxwl.core.repository.BaseDaoPlus;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysUserDaoPlus extends BaseDaoPlus {

	public List<Map<String, Object>> getUserPermissionList(Integer userId);
	
}
