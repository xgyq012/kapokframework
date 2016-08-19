package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmCaseInfo;
import com.nateiot.core.repository.BaseDao;

/**
 * 案发情况
 * 
 * @author guohuawen
 *
 */
public interface CisBmCaseDao extends BaseDao<CisBmCaseInfo, Integer>,
		CisBmCaseDaoPlus {
	
	@Query("select c from CisBmCaseInfo c where c.caseId in (:caseIds) " )
	public List<CisBmCaseInfo> queryListById(@Param("caseIds")List<Integer> caseIds);

}
