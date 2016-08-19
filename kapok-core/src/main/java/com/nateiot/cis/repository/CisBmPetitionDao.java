package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmPetitionInfo;
import com.nateiot.core.repository.BaseDao;

/**
 * 信访信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmPetitionDao extends BaseDao<CisBmPetitionInfo, Integer>,
		CisBmPetitionDaoPlus {
	
	@Query("select c from CisBmPetitionInfo c where c.petitionId in (:petitionIds) " )
	public List<CisBmPetitionInfo> queryListById(@Param("petitionIds")List<Integer> petitionIds);

}
