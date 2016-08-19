package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmOldPeopleH;
import com.nateiot.cis.repository.CisBmOldPeopleHDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmOldPeopleHService extends BaseService<CisBmOldPeopleHDao, CisBmOldPeopleH, Integer> {
	
	public CisBmOldPeopleH getCisBmOldPeopleHByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getOldPeopleHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
}
