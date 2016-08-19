package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmCsxjry;
import com.nateiot.core.repository.BaseDao;

public interface CisBmCsxjryDao extends BaseDao<CisBmCsxjry, Integer> , CisBmCsxjryDaoPlus {
	
	@Query("select c from CisBmCsxjry c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmCsxjry findByHouseholderId(@Param("id") int householderId);
	
}
