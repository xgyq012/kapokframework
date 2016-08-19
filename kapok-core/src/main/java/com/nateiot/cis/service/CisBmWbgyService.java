package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmWbgy;
import com.nateiot.cis.repository.CisBmWbgyDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmWbgyService  extends BaseService<CisBmWbgyDao, CisBmWbgy, Integer> {
	
	public CisBmWbgy getCisBmWbgyByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getWbgyHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
}
