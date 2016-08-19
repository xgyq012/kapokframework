package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmSafetyInfo;
import com.nateiot.core.repository.BaseDao;

/**
 * 技防信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmSafetyDao extends BaseDao<CisBmSafetyInfo, Integer>,
		CisBmSafetyDaoPlus {
	
	@Query("select c from CisBmSafetyInfo c where c.safetyId in (:safetyIds) " )
	public List<CisBmSafetyInfo> queryListById(@Param("safetyIds")List<Integer> safetyIds);

}
