package com.nateiot.base.service;

import java.util.List;
import java.util.Map;

import com.nateiot.base.domain.GxwlSysResource;
import com.nateiot.base.repository.GxwlSysResourceDao;
import com.nateiot.core.service.BaseService;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysResourceService extends
		BaseService<GxwlSysResourceDao, GxwlSysResource, Integer> {

	public Map<String, Object> getCurrentUserMenuByParenResourceId(Integer parentResourceId);

	public List<GxwlSysResource> getGxwlSysResourceByParenResourceId(Integer parentResourceId);
	
	public Map<String, Object> getCurrentUserMenuByResourceType(String resourceType);
	
	public List<Map<String, Object>> getCurrentUserMenu();
	
	public List<Map<String, Object>> getCurrentUserMenuByModule(Integer  menuId); 
	
	public List<Map<String, Object>> getCurrentUserMenuByModuleParent(Integer menuId);
	
	public Map<String, Object> getMenu();
	

}
