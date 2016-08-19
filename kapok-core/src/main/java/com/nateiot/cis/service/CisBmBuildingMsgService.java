package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.cis.repository.CisBmBuildingMsgDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmBuildingMsgService  extends BaseService<CisBmBuildingMsgDao, CisBmBuildingMsg, Integer>{
	
	public Map<String, Object>  search (Map<String, SearchFilter> conditions, Pageable pageable);
	
	public Map<String, Object> softDel(List<Integer> ids);
	
	public Map<String, Object>  softDel(Integer id);
	
	public Map<String, Object> delList(List<Integer> ids);
}
