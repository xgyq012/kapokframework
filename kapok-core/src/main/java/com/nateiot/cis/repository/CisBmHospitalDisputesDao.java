package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmHospitalDisputes;
import com.nateiot.core.repository.BaseDao;

/**
 * 医院纠纷
 * 
 * @author Administrator
 *
 */
public interface CisBmHospitalDisputesDao extends BaseDao<CisBmHospitalDisputes, Integer>,
         CisBmHospitalDisputesDaoPlus {
	
	@Query("select c from CisBmHospitalDisputes c where c.disputesId in (:disputesIds) " )
	public List<CisBmHospitalDisputes> queryListById(@Param("disputesIds")List<Integer> disputesIds);

}
