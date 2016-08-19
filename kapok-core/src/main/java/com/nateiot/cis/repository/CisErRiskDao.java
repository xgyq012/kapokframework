package com.nateiot.cis.repository;

import com.nateiot.cis.domain.CisErRisk;
import com.nateiot.core.repository.BaseDao;

/**
 * 项目风险评估
 * 
 *  @author Guohw
 */

public interface CisErRiskDao extends 
		BaseDao<CisErRisk, Integer>, CisErRiskDaoPlus{

	public CisErRisk findByRiskId(Integer riskId);

}
