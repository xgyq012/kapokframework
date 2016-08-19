package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmEntrepreneurStar;
import com.nateiot.cis.repository.CisBmEntrepreneurStarDao;
import com.nateiot.core.service.BaseService;

/**
 * 创业之星
 * 
 * @author Administrator
 *
 */
public interface CisBmEntrepreneurStarService extends
	BaseService<CisBmEntrepreneurStarDao, CisBmEntrepreneurStar, Integer> {
	
	public Map<String, Object> softDel(Integer entrepreneurStarId);
	
	public Map<String, Object> softDelList(List<Integer> entrepreneurStarIds);
	
}
