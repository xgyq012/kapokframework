package com.gdgxwl.base.repository;

import com.gdgxwl.core.repository.BaseDaoPlus;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysDataPDaoPlus extends BaseDaoPlus {
	
	public List<Map<String, Object>> getCurrentUserDataByResourceId(Integer userId, Integer resourceId);

}
