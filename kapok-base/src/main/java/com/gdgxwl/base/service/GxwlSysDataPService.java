package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysDataP;
import com.gdgxwl.base.repository.GxwlSysDataPDao;
import com.gdgxwl.core.service.BaseService;

import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysDataPService extends
		BaseService<GxwlSysDataPDao, GxwlSysDataP, Integer> {
	
	public Map<String, Object> getCurrentUserResourceHeaderByResourceCode(String resourceCode);
	
	public Map<String, Object> getCurrentUserResourceSqlWhereByResourceCode(String resourceCode);
}
