package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisBmDieinfo;
import com.nateiot.cis.repository.CisBmDieinfoDao;
import com.nateiot.core.service.BaseService;

/**
 * 死亡人員信息
 * @author xiaguangjun
 *
 */
public interface CisBmDieinfoService extends BaseService< CisBmDieinfoDao,  CisBmDieinfo, Integer>{
	
	public CisBmDieinfo getCisBmDieinfoByHouseholderId(int householderId);
	public Map<String,Object> softDel(int householderId);
	
}
