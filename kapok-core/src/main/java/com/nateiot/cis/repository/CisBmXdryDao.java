package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmXdry;
import com.nateiot.core.repository.BaseDao;

public interface CisBmXdryDao extends BaseDao<CisBmXdry, Integer > , CisBmXdryDaoPlus {
	
	@Query("select c from CisBmXdry c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmXdry findByHouseholderId(@Param("id") int householderId);
	
}
