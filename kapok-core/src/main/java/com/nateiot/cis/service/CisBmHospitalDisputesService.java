package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmHospitalDisputes;
import com.nateiot.cis.repository.CisBmHospitalDisputesDao;
import com.nateiot.core.service.BaseService;

/**
 * 医院纠纷
 * 
 * @author Administrator
 *
 */
public interface CisBmHospitalDisputesService extends
	BaseService<CisBmHospitalDisputesDao, CisBmHospitalDisputes, Integer> {
	
	public Map<String, Object> softDel(Integer hoDisputesId);
	
	public Map<String, Object> softDelList(List<Integer> hoDisputesIds);
	
}
