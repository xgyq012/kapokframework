package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmLeftoverChildren;
import com.nateiot.cis.repository.CisBmLeftoverChildrenDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmLeftoverChildrenService extends BaseService<CisBmLeftoverChildrenDao, CisBmLeftoverChildren, Integer> {

	public CisBmLeftoverChildren getCisBmLeftoverChildrenByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getLeftoverChildren(Map<String, SearchFilter> conditions, Pageable pageable );
	
}
