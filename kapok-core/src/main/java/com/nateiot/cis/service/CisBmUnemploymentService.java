package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmUnemployment;
import com.nateiot.cis.repository.CisBmUnemploymentDao;
import com.nateiot.core.service.BaseService;

/**
 * 单位信息
 * 
 * @author Administrator
 *
 */
public interface CisBmUnemploymentService extends
	BaseService<CisBmUnemploymentDao, CisBmUnemployment, Integer> {
	
	//public Map<String, Object> softDel(Integer unemploymentId);
	
	//public Map<String, Object> softDelList(List<Integer> unemploymentIds);
	
}
