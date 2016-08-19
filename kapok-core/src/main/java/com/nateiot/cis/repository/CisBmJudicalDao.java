package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmJudicialInfo;
import com.nateiot.core.repository.BaseDao;

/**
 * 司法信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmJudicalDao extends BaseDao<CisBmJudicialInfo, Integer>,
		CisBmJudicalDaoPlus {

	@Query("select c from CisBmJudicialInfo c where c.judicialId in (:judicialId) " )
	public List<CisBmJudicialInfo> queryListById(@Param("judicialId")List<Integer> judicalIds);
}
