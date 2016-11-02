package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysDictH;
import com.gdgxwl.base.repository.GxwlSysDictHDao;
import com.gdgxwl.core.service.BaseService;
import org.joda.time.DateTime;

import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysDictHService extends
		BaseService<GxwlSysDictHDao, GxwlSysDictH, Integer> {
	
	public Map<String, Object> getDictByDictTypeCode(String dictTypeCode, DateTime lastSyncTime);
	
	public Map<String, Object> syncDict();
}
