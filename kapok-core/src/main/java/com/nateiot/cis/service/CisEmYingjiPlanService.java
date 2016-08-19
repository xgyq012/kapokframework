package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisEmYingjiPlan;
import com.nateiot.cis.repository.CisEmYingjiPlanDao;
import com.nateiot.core.service.BaseService;

public interface CisEmYingjiPlanService extends BaseService<CisEmYingjiPlanDao, CisEmYingjiPlan, Integer> {
	public Map<String, Object> doDelete(List<Integer> ids);

}
