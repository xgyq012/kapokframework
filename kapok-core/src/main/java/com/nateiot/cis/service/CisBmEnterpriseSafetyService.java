package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmEnterpriseSafety;
import com.nateiot.cis.repository.CisBmEnterpriseSafetyDao;
import com.nateiot.core.service.BaseService;

/**
 * 企业安全
 * 
 * @author Administrator
 *
 */
public interface CisBmEnterpriseSafetyService extends
	BaseService<CisBmEnterpriseSafetyDao, CisBmEnterpriseSafety, Integer> {
	
	public Map<String, Object> softDel(Integer saEnterpriseId);
	
	public Map<String, Object> softDelList(List<Integer> saEnterpriseIds);
	
}
