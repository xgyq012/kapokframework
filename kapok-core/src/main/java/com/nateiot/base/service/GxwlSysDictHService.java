package com.nateiot.base.service;

import java.util.Map;

import org.joda.time.DateTime;

import com.nateiot.base.domain.GxwlSysDictH;
import com.nateiot.base.repository.GxwlSysDictHDao;
import com.nateiot.core.service.BaseService;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysDictHService extends
		BaseService<GxwlSysDictHDao, GxwlSysDictH, Integer> {
	
	public Map<String, Object> getDictByDictTypeCode(String dictTypeCode, DateTime lastSyncTime);
	
	public Map<String, Object> syncDict();
}
