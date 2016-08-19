package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisErRiskStatus;
import com.nateiot.cis.repository.CisErRiskStatusDao;
import com.nateiot.core.service.BaseService;

/**
 * 项目风险状态
 * 
 *  @author Guohw
 */

public interface CisErRiskStatusService extends 
		BaseService<CisErRiskStatusDao, CisErRiskStatus, Integer>{
	
	/**
	 * 判定状态 
	 */
	public Map<String, Object> distinguish(Integer riskId);
	
	/**
	 * 保存
	 */
	public Map<String, Object> statusSave(CisErRiskStatus cisErRiskStatus);
	
	/**
	 * 加载详细 
	 */
	public Map<String, Object> statusSelect(Integer riskId);
	
	/**
	 * 提交不通过 
	 */
	public Map<String, Object> noPass(CisErRiskStatus cisErRiskStatus);
	
	/**
	 * 加载审核信息 
	 */
//	public Map<String, Object> getStatusAudit(Integer riskId);
}
