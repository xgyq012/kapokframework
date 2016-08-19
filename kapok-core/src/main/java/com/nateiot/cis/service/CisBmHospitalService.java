package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmHospital;
import com.nateiot.cis.repository.CisBmHospitalDao;
import com.nateiot.core.service.BaseService;

/**
 * 医院信息
 * 
 * @author Administrator
 *
 */
public interface CisBmHospitalService extends
	BaseService<CisBmHospitalDao, CisBmHospital, Integer> {
	
	public Map<String, Object> softDel(Integer hospitalId);
	
	public Map<String, Object> delList(List<Integer> ids);
	
}
