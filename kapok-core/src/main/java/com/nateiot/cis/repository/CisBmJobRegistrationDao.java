package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmCultivate;
import com.nateiot.cis.domain.CisBmJobRegistration;
import com.nateiot.core.repository.BaseDao;

/**
 * 求职登记
 * 
 * @author Administrator
 *
 */
public interface CisBmJobRegistrationDao extends BaseDao<CisBmJobRegistration, Integer>, CisBmJobRegistrationDaoPlus {
	
	@Query("select c from  CisBmJobRegistration c where c.jobregId in (:jobregId) " )
	public List<CisBmJobRegistration> queryListById(@Param("jobregId")List<Integer> jobRegistrationIds);

}
