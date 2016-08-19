package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisBmUnits;
import com.nateiot.cis.repository.CisBmUnitsDao;
import com.nateiot.core.service.BaseService;

/**
 * 单位信息
 * 
 * @author Administrator
 *
 */
public interface CisBmUnitsService extends
	BaseService<CisBmUnitsDao, CisBmUnits, Integer> {
	
	public Map<String, Object> softDel(Integer unitsId);
	
	public Map<String, Object> count(String meshIds);
}
