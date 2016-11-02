package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysResource;
import com.gdgxwl.base.repository.GxwlSysResourceDao;
import com.gdgxwl.core.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysResourceService extends
		BaseService<GxwlSysResourceDao, GxwlSysResource, Integer> {

	public Map<String, Object> getCurrentUserMenuByParenResourceId(Integer parentResourceId);

	public List<GxwlSysResource> getGxwlSysResourceByParenResourceId(Integer parentResourceId);

	public Map<String, Object> getCurrentUserMenuByResourceType(String resourceType, String systemType);
	
	public List<Map<String, Object>> getCurrentUserMenu();
	
	public List<Map<String, Object>> getCurrentUserMenuByModule(Integer  menuId); 
	
	public List<Map<String, Object>> getCurrentUserMenuByModuleParent(Integer menuId);
	
	public Map<String, Object> getMenu();
	

}
