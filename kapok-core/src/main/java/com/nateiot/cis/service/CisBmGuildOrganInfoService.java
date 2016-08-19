package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmGuildOrganInfo;
import com.nateiot.cis.repository.CisBmGuildOrganInfoDao;
import com.nateiot.core.service.BaseService;

public interface CisBmGuildOrganInfoService  extends BaseService<CisBmGuildOrganInfoDao , CisBmGuildOrganInfo , Integer>  {
	
	public Map<String, Object> delList(List<Integer> ids);
	
}
