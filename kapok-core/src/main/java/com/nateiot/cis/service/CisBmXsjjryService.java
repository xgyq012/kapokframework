package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisBmXsjjry;
import com.nateiot.cis.repository.CisBmXsjjryDao;
import com.nateiot.core.service.BaseService;

public interface CisBmXsjjryService  extends BaseService< CisBmXsjjryDao,  CisBmXsjjry, Integer>{
	
	public CisBmXsjjry getCisBmXsjjryByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);

}
