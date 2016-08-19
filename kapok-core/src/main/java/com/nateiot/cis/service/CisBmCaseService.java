package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmCaseInfo;
import com.nateiot.cis.repository.CisBmCaseDao;
import com.nateiot.core.service.BaseService;

/**
 * 案发情况
 * 
 * @author guohuawen
 *
 */
public interface CisBmCaseService extends
	BaseService<CisBmCaseDao, CisBmCaseInfo, Integer> {
	
	public Map<String, Object> softDel(Integer caseId);
	
	public Map<String, Object> softDelList(List<Integer> caseIds);
	
}
