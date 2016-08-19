package com.nateiot.base.service;

import java.util.Map;

import com.nateiot.base.domain.GxwlSysDataP;
import com.nateiot.base.repository.GxwlSysDataPDao;
import com.nateiot.core.service.BaseService;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysDataPService extends
		BaseService<GxwlSysDataPDao, GxwlSysDataP, Integer> {
	
	public Map<String, Object> getCurrentUserResourceHeaderByResourceCode(String resourceCode);
	
	public Map<String, Object> getCurrentUserResourceSqlWhereByResourceCode(String resourceCode);
}
