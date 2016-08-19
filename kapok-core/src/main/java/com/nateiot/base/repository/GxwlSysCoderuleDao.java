package com.nateiot.base.repository;

import com.nateiot.base.domain.GxwlSysCoderule;
import com.nateiot.core.repository.BaseDao;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysCoderuleDao extends BaseDao<GxwlSysCoderule, Integer>,
		GxwlSysCoderuleDaoPlus {
	
	public GxwlSysCoderule findByCoderuleCode(String coderuleCode);

}
