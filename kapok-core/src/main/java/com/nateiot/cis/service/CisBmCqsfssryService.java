package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisBmCqsfssry;
import com.nateiot.cis.repository.CisBmCqsfssryDao;
import com.nateiot.core.service.BaseService;

public interface CisBmCqsfssryService extends BaseService< CisBmCqsfssryDao,  CisBmCqsfssry, Integer>{
	
	public CisBmCqsfssry getCisBmCqsfssryByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId); 

}
