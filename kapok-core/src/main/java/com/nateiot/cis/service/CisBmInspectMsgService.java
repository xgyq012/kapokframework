package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmInspectMsg;
import com.nateiot.cis.repository.CisBmInspectMsgDao;
import com.nateiot.core.service.BaseService;

public interface CisBmInspectMsgService extends BaseService<CisBmInspectMsgDao, CisBmInspectMsg, Integer> {
	
	public Map<String, Object> delList(List<Integer> ids);
	
}
