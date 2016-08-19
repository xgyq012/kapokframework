package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmEnterpriseSafety;
import com.nateiot.core.repository.BaseDao;

/**
 * 企业安全
 * 
 * @author Administrator
 *
 */
public interface CisBmEnterpriseSafetyDao extends BaseDao<CisBmEnterpriseSafety, Integer>,
         CisBmEnterpriseSafetyDaoPlus {
	
	@Query("select c from CisBmEnterpriseSafety c where c.saenterpriseId in (:saenterpriseIds) " )
	public List<CisBmEnterpriseSafety> queryListById(@Param("saenterpriseIds")List<Integer> saenterpriseIds);

}
