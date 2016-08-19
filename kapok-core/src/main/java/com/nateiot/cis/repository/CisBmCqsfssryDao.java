package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmCqsfssry;
import com.nateiot.core.repository.BaseDao;

public interface CisBmCqsfssryDao extends BaseDao< CisBmCqsfssry, Integer> , CisBmCqsfssryDaoPlus {
	
	@Query("select c from CisBmCqsfssry c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmCqsfssry findByHouseholderId(@Param("id") int householderId);
	
}
