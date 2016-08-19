package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmComfort;
import com.nateiot.core.repository.BaseDao;

public interface CisBmComfortDao extends BaseDao<CisBmComfort, Integer> , CisBmComfortDaoPlus{
	
	@Query("select c from CisBmComfort c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmComfort findByHouseholderId(@Param("id") int householderId);
}
