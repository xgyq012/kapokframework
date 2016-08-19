package com.nateiot.cis.service;

import com.nateiot.cis.domain.CisBmHealthinSuranceinfo;
import com.nateiot.cis.repository.CisBmHealthinSuranceinfoDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 医保信息 
 * @author xiaguangjun
 *
 */
public interface CisBmHealthinSuranceinfoService
		extends BaseService<CisBmHealthinSuranceinfoDao, CisBmHealthinSuranceinfo, Integer> {

	public CisBmHealthinSuranceinfo getCisBmHealthinSuranceinfoByHouseholderId(int householderId);
	
	public Map<String,Object> softDel(int householderId);

	public Map<String, Object> searchHolderHealthSuranceinfo(Map<String, SearchFilter> conditions, Pageable pageable);

	public Map<String,Object> getCisBmHealthinSuranceMsg(int householderId);
	
}
