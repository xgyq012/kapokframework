package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisErProInvest;
import com.nateiot.cis.repository.CisErProInvestDao;
import com.nateiot.core.service.BaseService;

/**
 * 经济运行 -- 项目招商
 * 
 *  @author guohuawen
 */
public interface CisErProInvestService extends
		BaseService<CisErProInvestDao, CisErProInvest, Integer>{
	/**
	 * 软删除 
	 */
	public Map<String, Object> softDel(Integer proInvestId);

}
