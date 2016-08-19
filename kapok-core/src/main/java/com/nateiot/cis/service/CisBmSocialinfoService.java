package com.nateiot.cis.service;

import com.nateiot.cis.domain.CisBmSocialinfo;
import com.nateiot.cis.repository.CisBmSocialinfoDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CisBmSocialinfoService extends BaseService<CisBmSocialinfoDao, CisBmSocialinfo, Integer> {
	
	public CisBmSocialinfo getCisBmSocialinfoByHouseholderId(int householderId);
	
	public Map<String,Object> softDel(int householderId);

	public Map<String,Object> getCisBmSocialinfoByholderIdMsg(int householderId);

	public Map<String, Object> searchHolderSocialinfo(Map<String, SearchFilter> conditions, Pageable pageable);
}
