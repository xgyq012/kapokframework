package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmGuaranteedLoan;
import com.nateiot.cis.repository.CisBmGuaranteedLoanDao;
import com.nateiot.core.service.BaseService;

/**
 * 小额担保贷款
 * 
 * @author Administrator
 *
 */
public interface CisBmGuaranteedLoanService extends
	BaseService<CisBmGuaranteedLoanDao, CisBmGuaranteedLoan, Integer> {
	
	public Map<String, Object> softDel(Integer guaranteedId);
	
	public Map<String, Object> softDelList(List<Integer> guaranteedIds);
	
}
