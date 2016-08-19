package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmCampusSafety;
import com.nateiot.cis.repository.CisBmCampusSafetyDao;
import com.nateiot.core.service.BaseService;

/**
 * 公共安全--校园安全
 * 
 * @author Administrator
 *
 */
public interface CisBmCampusSafetyService extends
	BaseService<CisBmCampusSafetyDao, CisBmCampusSafety, Integer> {
	
	public Map<String, Object> softDel(Integer saCampusId);
	
	public Map<String, Object> softDelList(List<Integer> saCampusIds);
	
}
