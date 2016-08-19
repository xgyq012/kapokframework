package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmXdry;
import com.nateiot.cis.repository.CisBmXdryDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmXdryService extends BaseService<CisBmXdryDao , CisBmXdry , Integer>{
	
	public CisBmXdry getCisBmXdryByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getXdryHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
	
}
