package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmFireControl;
import com.nateiot.cis.repository.CisBmFireControlDao;
import com.nateiot.core.service.BaseService;

/**
 * 消防信息
 * 
 * @author Administrator
 *
 */
public interface CisBmFireControlService extends
	BaseService<CisBmFireControlDao, CisBmFireControl, Integer> {
	
	public Map<String, Object> softDel(Integer fireControlId);
	
	public Map<String, Object> softDelList(List<Integer> fireControlIds);
	
	public Map<String, Object> count(String meshIds);
}
