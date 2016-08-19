package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmSpecial;
import com.nateiot.cis.repository.CisBmSpecialDao;
import com.nateiot.core.service.BaseService;

/**
 * 特殊行业
 * 
 * @author Administrator
 *
 */
public interface CisBmSpecialService extends
	BaseService<CisBmSpecialDao, CisBmSpecial, Integer> {
	
	public Map<String, Object> softDel(Integer specialId);
	
	public Map<String, Object> delList(List<Integer> ids);	
	
}
