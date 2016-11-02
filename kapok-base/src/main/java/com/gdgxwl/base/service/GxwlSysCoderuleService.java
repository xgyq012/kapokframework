package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysCoderule;
import com.gdgxwl.base.repository.GxwlSysCoderuleDao;
import com.gdgxwl.core.service.BaseService;

/**
 * @author Will WM. Zhang
 *
 */
public interface GxwlSysCoderuleService extends
		BaseService<GxwlSysCoderuleDao, GxwlSysCoderule, Integer> {

	public String generateCode(String coderuleCode);
	
}
