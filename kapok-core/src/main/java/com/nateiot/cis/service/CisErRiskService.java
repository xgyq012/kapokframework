package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisErRisk;
import com.nateiot.cis.repository.CisErRiskDao;
import com.nateiot.core.service.BaseService;


/**
 * 风险项目评估
 * 
 *  @author Guohw
 */

public interface CisErRiskService extends
			BaseService<CisErRiskDao, CisErRisk, Integer> {
	
	/**
	 * 软删除 
	 */
	public Map<String, Object> softDel(Integer riskId);

	/**
	 * 保存 
	 */
	public Map<String, Object> riskSave(CisErRisk cisErRisk);
	
	/**
	 * 详细 
	 */
	public Map<String, Object> riskSelect(Integer riskId);

}
