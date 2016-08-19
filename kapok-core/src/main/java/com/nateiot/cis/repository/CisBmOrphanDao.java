package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmOrphan;
import com.nateiot.core.repository.BaseDao;

public interface CisBmOrphanDao extends BaseDao<CisBmOrphan, Integer>,CisBmOrphanDaoPlus {

	@Query("select c from CisBmOrphan c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmOrphan findByHouseholderId(@Param("id") int householderId);
	
}
