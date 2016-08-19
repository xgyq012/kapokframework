package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmCommunityMsg;
import com.nateiot.cis.repository.CisBmCommunityMsgDao;
import com.nateiot.core.service.BaseService;

public interface CisBmCommunityMsgService extends  BaseService< CisBmCommunityMsgDao, 
	CisBmCommunityMsg, Integer>{
	
	public Map<String, Object> softDel(List<Integer> ids);

	public Map<String, Object> softDel(Integer id);
	
}
