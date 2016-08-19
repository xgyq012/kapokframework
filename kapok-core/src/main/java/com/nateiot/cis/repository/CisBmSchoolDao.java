package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmHouseMsg;
import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.core.repository.BaseDao;

/**
 * 学校信息
 * 
 * @author Administrator
 *
 */
public interface CisBmSchoolDao extends BaseDao<CisBmSchool, Integer>,
		CisBmSchoolDaoPlus {

	@Query("select c from  CisBmSchool c where c.schoolId in (:ids) " )
	public List<CisBmSchool> queryListById(@Param("ids")List<Integer> ids);
	
}
