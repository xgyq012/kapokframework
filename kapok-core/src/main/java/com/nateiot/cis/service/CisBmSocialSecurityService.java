package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmSocialSecurity;
import com.nateiot.cis.repository.CisBmSocialSecurityDao;
import com.nateiot.core.service.BaseService;

/**
 * 社保信息
 * 
 * @author Administrator
 *
 */
public interface CisBmSocialSecurityService extends
	BaseService<CisBmSocialSecurityDao, CisBmSocialSecurity, Integer> {
	
	public Map<String, Object> softDel(Integer socialSecurityId);
	
	public Map<String, Object> softDelList(List<Integer> socialSecurityIds);
	
}
