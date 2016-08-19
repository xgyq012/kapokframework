package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmCsxjry;
import com.nateiot.cis.repository.CisBmCsxjryDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmCsxjryService extends BaseService<CisBmCsxjryDao , CisBmCsxjry , Integer> {
	
	public CisBmCsxjry getCisBmCsxjryByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getCsxjryHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
}
