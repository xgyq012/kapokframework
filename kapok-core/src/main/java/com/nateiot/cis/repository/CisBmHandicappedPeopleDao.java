package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmHandicappedPeople;
import com.nateiot.core.repository.BaseDao;

public interface CisBmHandicappedPeopleDao extends BaseDao<CisBmHandicappedPeople, Integer>,CisBmHandicappedPeopleDaoPlus {
	
	@Query("select c from CisBmHandicappedPeople c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmHandicappedPeople findByHouseholderId(@Param("id") int householderId);
	
}
