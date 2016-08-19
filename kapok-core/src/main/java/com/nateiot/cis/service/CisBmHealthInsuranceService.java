package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmHealthInsurance;
import com.nateiot.cis.repository.CisBmHealthInsuranceDao;
import com.nateiot.core.service.BaseService;

/**
 * 医保信息
 * 
 * @author Administrator
 *
 */
public interface CisBmHealthInsuranceService extends
	BaseService<CisBmHealthInsuranceDao, CisBmHealthInsurance, Integer> {
	
//	public Map<String, Object> softDel(Integer healthInsuranceId);
	
//	public Map<String, Object> softDelList(List<Integer> healthInsuranceIds);
	
}
