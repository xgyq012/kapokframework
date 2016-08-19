package com.nateiot.base.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.core.repository.BaseDaoPlus;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysResourceDaoPlus extends BaseDaoPlus {
	
	public List<Map<String, Object>> getCurrentUserMenuByParenResourceId(Integer userId, Integer parentResourceId);
	
	public List<Map<String, Object>> getCurrentUserMenuByResourceType(Integer userId, String resourceType);
	
	public List<Map<String, Object>> getCurrentUserMenu(Integer userId);
	
	public List<Map<String, Object>> getCurrentUserMenuByModule(Integer userId,Integer menuId);
	
	public List<Map<String, Object>> getCurrentUserMenuByModuleParent(Integer userId,Integer menuId);
	
}
