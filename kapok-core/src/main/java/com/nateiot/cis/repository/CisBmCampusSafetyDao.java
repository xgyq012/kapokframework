package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmCampusSafety;
import com.nateiot.core.repository.BaseDao;

/**
 * 公共安全--校园安全
 * 
 * @author Administrator
 *
 */
public interface CisBmCampusSafetyDao extends BaseDao<CisBmCampusSafety, Integer>,
         CisBmCampusSafetyDaoPlus {

	@Query("select c from CisBmCampusSafety c where c.sacampusId in (:sacampusIds) " )
	public List<CisBmCampusSafety> queryListById(@Param("sacampusIds")List<Integer> sacampusIds);
}
