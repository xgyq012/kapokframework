package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmDieinfo;
import com.nateiot.core.repository.BaseDao;

public interface CisBmDieinfoDao extends BaseDao<CisBmDieinfo, Integer>, CisBmDieinfoDaoPlus {
	
	
	@Query("select c from CisBmDieinfo c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmDieinfo findByHouseholderId(@Param("id") int householderId);
	
}
