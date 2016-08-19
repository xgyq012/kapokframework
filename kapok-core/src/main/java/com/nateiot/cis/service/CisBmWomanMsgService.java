package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmWomanMsg;
import com.nateiot.cis.repository.CisBmWomanMsgDao;
import com.nateiot.core.service.BaseService;

public interface CisBmWomanMsgService  extends BaseService<CisBmWomanMsgDao,CisBmWomanMsg,Integer> {
	
	public Map<String, Object> delList(List<Integer> ids);
	
}
