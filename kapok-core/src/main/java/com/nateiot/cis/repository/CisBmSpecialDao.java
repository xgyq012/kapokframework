package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.cis.domain.CisBmSpecial;
import com.nateiot.core.repository.BaseDao;

/**
 * 特殊行业
 * 
 * @author Administrator
 *
 */
public interface CisBmSpecialDao extends BaseDao<CisBmSpecial, Integer>,
		CisBmSpecialDaoPlus {
	
	
	@Query("select c from  CisBmSpecial c where c.specialId in (:ids) " )
	public List<CisBmSpecial> queryListById(@Param("ids")List<Integer> ids);

}
