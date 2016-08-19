package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmUnemployment;
import com.nateiot.core.repository.BaseDao;

/**
 * 失业证办理
 * 
 * @author Administrator
 *
 */
public interface CisBmUnemploymentDao extends BaseDao<CisBmUnemployment, Integer>,
         CisBmUnemploymentDaoPlus {
	
	@Query("select c from CisBmUnemployment c where c.unemploymentId in (:unemploymentIds) " )
	public List< CisBmUnemployment> queryListById(@Param("unemploymentIds")List<Integer> unemploymentIds);

}
