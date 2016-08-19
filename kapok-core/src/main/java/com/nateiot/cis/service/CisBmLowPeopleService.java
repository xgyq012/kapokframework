package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmLowPeople;
import com.nateiot.cis.repository.CisBmLowPeopleDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmLowPeopleService extends BaseService<CisBmLowPeopleDao, CisBmLowPeople, Integer> {

	public CisBmLowPeople getCisBmCisBmLowPeopleByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getLowPeopleHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
}
