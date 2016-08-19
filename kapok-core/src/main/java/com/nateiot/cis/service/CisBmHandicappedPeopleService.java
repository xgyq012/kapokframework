package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmDieinfo;
import com.nateiot.cis.domain.CisBmHandicappedPeople;
import com.nateiot.cis.repository.CisBmHandicappedPeopleDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmHandicappedPeopleService extends BaseService<CisBmHandicappedPeopleDao,CisBmHandicappedPeople, Integer>{

	public CisBmHandicappedPeople getCisBmHandicappedPeopleByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getHandicappedPeopleHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
}
