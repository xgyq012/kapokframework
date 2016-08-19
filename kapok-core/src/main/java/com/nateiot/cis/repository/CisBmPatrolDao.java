package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmPatrolInfo;
import com.nateiot.core.repository.BaseDao;

/**
 * 巡逻队信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmPatrolDao extends BaseDao<CisBmPatrolInfo, Integer>,
		CisBmPatrolDaoPlus {
	
	@Query("select c from CisBmPatrolInfo c where c.patrolId in (:patrolIds) " )
	public List<CisBmPatrolInfo> queryListById(@Param("patrolIds")List<Integer> patrolIds);

}
