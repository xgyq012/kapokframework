package com.gdgxwl.base.repository;

import com.gdgxwl.core.repository.BaseDaoPlus;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 *
 */
public interface GxwlSysDictHDaoPlus extends BaseDaoPlus {
	
	public List<Map<String, Object>> getDictByDictTypeCode(String dictTypeCode);
	
	public List<Map<String, Object>> getDictTypeList();
	
	public List<Map<String, Object>> getDictList();

}
