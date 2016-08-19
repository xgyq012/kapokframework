package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisEmPlanMx;
import com.nateiot.cis.repository.CisEmPlanMxDao;
import com.nateiot.core.service.BaseService;

public interface CisEmPlanMxService extends BaseService<CisEmPlanMxDao, CisEmPlanMx, Integer>{
	public Map<String, Object> findByYingjiPlanId(Integer yingjiPlanId);
}
