package com.nateiot.base.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.core.repository.BaseDaoPlus;

/**
 * @author Will WM. Zhang
 *
 */
public interface GxwlSysDictHDaoPlus extends BaseDaoPlus {
	
	public List<Map<String, Object>> getDictByDictTypeCode(String dictTypeCode);
	
	public List<Map<String, Object>> getDictTypeList();
	
	public List<Map<String, Object>> getDictList();

}
