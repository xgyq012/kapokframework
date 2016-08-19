package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmSafetyInfo;
import com.nateiot.cis.repository.CisBmSafetyDao;
import com.nateiot.core.service.BaseService;

/**
 * 技防信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmSafetyService extends
	BaseService<CisBmSafetyDao, CisBmSafetyInfo, Integer> {
	
	public Map<String, Object> softDel(Integer safetyId);
	
	public Map<String, Object> softDelList(List<Integer> safetyIds);
	
}
