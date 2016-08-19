package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmEntrepreneurShip;
import com.nateiot.cis.repository.CisBmEntrepreneurShipDao;
import com.nateiot.core.service.BaseService;

/**
 * 创业商户
 * 
 * @author Administrator
 *
 */
public interface CisBmEntrepreneurShipService extends
	BaseService<CisBmEntrepreneurShipDao, CisBmEntrepreneurShip, Integer> {
	
	public Map<String, Object> softDel(Integer entrepreneurShipId);
	
	public Map<String, Object> softDelList(List<Integer> entrepreneurShipIds);
	
}
