package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmSqjzdx;
import com.nateiot.cis.repository.impl.CisBmSqjzdxDaoPlus;
import com.nateiot.core.repository.BaseDao;

public interface CisBmSqjzdxDao extends BaseDao<CisBmSqjzdx, Integer> , CisBmSqjzdxDaoPlus{
	
	@Query("select c from CisBmSqjzdx c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmSqjzdx findByHouseholderId(@Param("id") int householderId);
	
}
