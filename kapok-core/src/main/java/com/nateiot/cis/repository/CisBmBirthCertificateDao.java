package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmBirthCertificate;
import com.nateiot.core.repository.BaseDao;

public interface CisBmBirthCertificateDao extends BaseDao<CisBmBirthCertificate, Integer> , CisBmBirthCertificateDaoPlus {

	@Query("select c from  CisBmBirthCertificate c where c.birthId in (:ids) " )
	public List<CisBmBirthCertificate> queryListById(@Param("ids")List<Integer> ids);
	
}
