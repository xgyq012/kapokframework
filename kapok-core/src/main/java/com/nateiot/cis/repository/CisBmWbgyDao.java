package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmComfort;
import com.nateiot.cis.domain.CisBmWbgy;
import com.nateiot.core.repository.BaseDao;

public interface CisBmWbgyDao extends BaseDao<CisBmWbgy, Integer>, CisBmWbgyDaoPlus {

	@Query("select c from CisBmWbgy c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmWbgy findByHouseholderId(@Param("id") int householderId);
	
}
