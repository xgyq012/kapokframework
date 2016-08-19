package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmPartyMemberInfo;
import com.nateiot.cis.repository.CisBmPartyMemberInfoDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmPartyMemberInfoService extends BaseService<CisBmPartyMemberInfoDao, CisBmPartyMemberInfo, Integer>  
{

	public Map<String, Object>  search (Map<String, SearchFilter> conditions, Pageable pageable);
	
	public Map<String, Object> delList(List<Integer> ids);
	
	public Map<String, Object> findByHouseholderId(Integer hid);
}
