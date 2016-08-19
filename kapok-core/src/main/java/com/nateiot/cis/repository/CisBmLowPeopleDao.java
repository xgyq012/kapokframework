package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmComfort;
import com.nateiot.cis.domain.CisBmLowPeople;
import com.nateiot.core.repository.BaseDao;

public interface CisBmLowPeopleDao extends BaseDao<CisBmLowPeople, Integer> , CisBmLowPeopleDaoPlus {
 
	@Query("select c from CisBmLowPeople c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmLowPeople findByHouseholderId(@Param("id") int householderId);
	
}
