package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmCqsfssry;
import com.nateiot.cis.domain.CisBmFxry;
import com.nateiot.core.repository.BaseDao;

public interface CisBmFxryDao extends BaseDao<CisBmFxry , Integer>,CisBmFxryDaoPlus{
	
	@Query("select c from CisBmFxry c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmFxry findByHouseholderId(@Param("id") int householderId);
	
}
