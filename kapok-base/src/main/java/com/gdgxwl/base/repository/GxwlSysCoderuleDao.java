package com.gdgxwl.base.repository;

import com.gdgxwl.base.domain.GxwlSysCoderule;
import com.gdgxwl.core.repository.BaseDao;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysCoderuleDao extends BaseDao<GxwlSysCoderule, Integer>,
		GxwlSysCoderuleDaoPlus {
	
	public GxwlSysCoderule findByCoderuleCode(String coderuleCode);

}
