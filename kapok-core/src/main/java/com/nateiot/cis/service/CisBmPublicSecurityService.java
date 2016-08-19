package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmPublicSecurity;
import com.nateiot.cis.repository.CisBmPublicSecurityDao;
import com.nateiot.core.service.BaseService;

/**
 * 治安信息
 * 
 * @author Administrator
 *
 */
public interface CisBmPublicSecurityService extends
	BaseService<CisBmPublicSecurityDao, CisBmPublicSecurity, Integer> {
	
	public Map<String, Object> softDel(Integer puSecurityId);
	
	public Map<String, Object> softDelList(List<Integer> puSecurityIds);
	
}
