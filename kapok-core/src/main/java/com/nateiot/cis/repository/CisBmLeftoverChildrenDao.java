package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmLeftoverChildren;
import com.nateiot.core.repository.BaseDao;

public interface CisBmLeftoverChildrenDao extends BaseDao<CisBmLeftoverChildren, Integer>,CisBmLeftoverChildrenDaoPlus{

	@Query("select c from CisBmLeftoverChildren c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmLeftoverChildren findByHouseholderId(@Param("id") int householderId);
	
}
