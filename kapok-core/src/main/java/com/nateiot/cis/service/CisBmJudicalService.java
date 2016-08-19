package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmJudicialInfo;
import com.nateiot.cis.repository.CisBmJudicalDao;
import com.nateiot.core.service.BaseService;

/**
 * 司法信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmJudicalService extends BaseService<CisBmJudicalDao, CisBmJudicialInfo, Integer> {
	
	public Map<String, Object> softDel(Integer judicalId);
	
	public Map<String, Object> softDelList(List<Integer> judicalIds);
	
}
