package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmComfort;
import com.nateiot.cis.repository.CisBmComfortDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmComfortService extends BaseService<CisBmComfortDao, CisBmComfort, Integer>{
	
	public CisBmComfort getCisBmComfortByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getComfortHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
}
