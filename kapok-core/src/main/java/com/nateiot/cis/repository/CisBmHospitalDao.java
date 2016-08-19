package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmHospital;
import com.nateiot.core.repository.BaseDao;

/**
 * 医院信息
 * 
 * @author Administrator
 *
 */
public interface CisBmHospitalDao extends BaseDao<CisBmHospital, Integer>,
		CisBmHospitalDaoPlus {
	

	@Query("select c from  CisBmHospital c where c.hospitalId in (:ids) " )
	public List<CisBmHospital> queryListById(@Param("ids")List<Integer> ids);

}
