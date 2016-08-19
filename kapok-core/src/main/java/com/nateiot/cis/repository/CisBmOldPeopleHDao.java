package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmOldPeopleH;
import com.nateiot.core.repository.BaseDao;

public interface CisBmOldPeopleHDao extends BaseDao<CisBmOldPeopleH,Integer>, CisBmOldPeopleHDaoPlus{
	
	@Query("select c from CisBmOldPeopleH c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmOldPeopleH findByHouseholderId(@Param("id") int householderId);
	
}
