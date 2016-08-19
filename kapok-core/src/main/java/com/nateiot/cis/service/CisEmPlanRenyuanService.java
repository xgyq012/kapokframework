package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisEmPlanRenyuan;
import com.nateiot.cis.repository.CisEmPlanRenyuanDao;
import com.nateiot.core.service.BaseService;

public interface CisEmPlanRenyuanService extends BaseService<CisEmPlanRenyuanDao, CisEmPlanRenyuan, Integer>{
	public Map<String, Object> save(String planRenyuanJson);

}
