package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmRecruitment;
import com.nateiot.core.repository.BaseDao;

/**
 * 单位招聘
 * 
 * @author Administrator
 *
 */
public interface CisBmRecruitmentDao extends BaseDao<CisBmRecruitment, Integer>, CisBmRecruitmentDaoPlus {
	
	@Query("select c from  CisBmRecruitment c where c.recruitmentId in (:recruitmentIds) " )
	public List<CisBmRecruitment> queryListById(@Param("recruitmentIds")List<Integer> recruitmentIds);

}
