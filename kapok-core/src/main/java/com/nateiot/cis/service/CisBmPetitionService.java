package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmPetitionInfo;
import com.nateiot.cis.repository.CisBmPetitionDao;
import com.nateiot.core.service.BaseService;

/**
 * 信访信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmPetitionService extends
	BaseService<CisBmPetitionDao, CisBmPetitionInfo, Integer> {
	
	public Map<String, Object> softDel(Integer petitionId);
	
	public Map<String, Object> softDelList(List<Integer> petitionIds);
	
}
