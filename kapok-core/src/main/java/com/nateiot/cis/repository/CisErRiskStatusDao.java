package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisErRiskStatus;
import com.nateiot.core.repository.BaseDao;

/**
 * 项目风险状态
 * 
 *  @author Guohw
 */
public interface CisErRiskStatusDao extends 
			BaseDao<CisErRiskStatus, Integer>, CisErRiskStatusDaoPlus{

	public List<CisErRiskStatus> findByRiskId(Integer id);

	public List<CisErRiskStatus> findByRiskIdAndDelSign(Integer riskId, String string);

}
