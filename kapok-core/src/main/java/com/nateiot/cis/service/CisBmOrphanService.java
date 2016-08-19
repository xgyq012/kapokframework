package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmOrphan;
import com.nateiot.cis.repository.CisBmOrphanDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmOrphanService extends BaseService<CisBmOrphanDao, CisBmOrphan, Integer> {

	public CisBmOrphan getCisBmOrphanByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getOrphanHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable );
}
