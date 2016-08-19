package com.nateiot.base.service;

import com.nateiot.base.domain.GxwlSysCoderule;
import com.nateiot.base.repository.GxwlSysCoderuleDao;
import com.nateiot.core.service.BaseService;

/**
 * @author Will WM. Zhang
 *
 */
public interface GxwlSysCoderuleService extends
		BaseService<GxwlSysCoderuleDao, GxwlSysCoderule, Integer> {

	public String generateCode(String coderuleCode);
	
}
