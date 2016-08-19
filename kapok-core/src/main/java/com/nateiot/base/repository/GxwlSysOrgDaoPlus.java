package com.nateiot.base.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.core.repository.BaseDaoPlus;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysOrgDaoPlus extends BaseDaoPlus {

	public List<Map<String, Object>> findRoleByOrgId(Integer orgId);
	
	public List<Map<String, Object>> findUserByOrgId(Integer orgId);
}
