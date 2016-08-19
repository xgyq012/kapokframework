package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisEmPlanMx;
import com.nateiot.core.repository.BaseDao;

public interface CisEmPlanMxDao extends BaseDao<CisEmPlanMx, Integer>, CisEmPlanMxDaoPlus{

	public List<CisEmPlanMx> findByYingjiPlanId(Integer yingjiPlanId);
}
