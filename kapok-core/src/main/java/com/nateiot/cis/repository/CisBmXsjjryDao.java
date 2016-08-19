package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmXsjjry;
import com.nateiot.core.repository.BaseDao;

public interface CisBmXsjjryDao extends BaseDao<CisBmXsjjry, Integer> , CisBmXsjjryDaoPlus {

	@Query("select c from  CisBmXsjjry c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public  CisBmXsjjry findByHouseholderId(@Param("id") int householderId);
	
}
