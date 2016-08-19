package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmSqjzdx;
import com.nateiot.cis.repository.CisBmSqjzdxDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmSqjzdxService  extends BaseService< CisBmSqjzdxDao,  CisBmSqjzdx, Integer>{
	
	public CisBmSqjzdx getCisBmSqjzdxByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getSqjzdxList(Map<String, SearchFilter> conditions, Pageable pageable );
}
