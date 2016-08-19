package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmWfjl;
import com.nateiot.core.repository.BaseDao;

public interface CisBmWfjlDao extends BaseDao<CisBmWfjl, Integer> , CisBmWfjlDaoPlus{
	
	@Query("select c from CisBmWfjl c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmWfjl findByHouseholderId(@Param("id") int householderId);
	
}
