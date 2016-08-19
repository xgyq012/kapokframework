package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmPatrolInfo;
import com.nateiot.cis.repository.CisBmPatrolDao;
import com.nateiot.core.service.BaseService;

/**
 * 巡逻队信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmPatrolService extends
	BaseService<CisBmPatrolDao, CisBmPatrolInfo, Integer> {
	
	public Map<String, Object> softDel(Integer patrolId);
	
	public Map<String, Object> softDelList(List<Integer> patrolIds);
	
}
