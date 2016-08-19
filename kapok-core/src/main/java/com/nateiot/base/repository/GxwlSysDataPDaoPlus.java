package com.nateiot.base.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.core.repository.BaseDaoPlus;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysDataPDaoPlus extends BaseDaoPlus {
	
	public List<Map<String, Object>> getCurrentUserDataByResourceId(Integer userId, Integer resourceId);

}
