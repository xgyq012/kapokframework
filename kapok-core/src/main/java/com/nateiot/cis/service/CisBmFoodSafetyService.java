package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmFoodSafety;
import com.nateiot.cis.repository.CisBmFoodSafetyDao;
import com.nateiot.core.service.BaseService;

/**
 * 食品安全
 * 
 * @author Administrator
 *
 */
public interface CisBmFoodSafetyService extends
	BaseService<CisBmFoodSafetyDao, CisBmFoodSafety, Integer> {
	
	public Map<String, Object> softDel(Integer saFoodsId);
	
	public Map<String, Object> softDelList(List<Integer> saFoodsIds);
	
}
