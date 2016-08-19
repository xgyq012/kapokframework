package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmFxry;
import com.nateiot.cis.repository.CisBmFxryDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmFxryService  extends BaseService<CisBmFxryDao , CisBmFxry , Integer> {
	
	public CisBmFxry getCisBmFxryByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getFxryHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
}
