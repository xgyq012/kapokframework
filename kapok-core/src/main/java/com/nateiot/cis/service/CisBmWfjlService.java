package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmWfjl;
import com.nateiot.cis.repository.CisBmWfjlDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmWfjlService extends BaseService<CisBmWfjlDao, CisBmWfjl, Integer> {

	public CisBmWfjl getCisBmWfjlByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	public Map<String,Object>  getWfqsnInfo(Map<String, SearchFilter> conditions, Pageable pageable );
}
