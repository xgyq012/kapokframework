package com.nateiot.base.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.core.repository.BaseDaoPlus;

/**
 * @author Will WM. Zhang
 *
 */
public interface GxwlSysRoleDaoPlus extends BaseDaoPlus{

	public List<Map<String, Object>> getUsersByRoleId(Integer roleId);
	
	public List<Map<String, Object>> getGxwlSysResourceByRoleId(Integer roleId);
	
}
