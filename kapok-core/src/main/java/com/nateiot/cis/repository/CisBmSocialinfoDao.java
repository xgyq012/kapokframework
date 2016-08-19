package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmSocialinfo;
import com.nateiot.core.repository.BaseDao;

public interface CisBmSocialinfoDao extends BaseDao<CisBmSocialinfo, Integer> ,CisBmSocialinfoDaoPlus{
	
	@Query("select c from CisBmSocialinfo c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmSocialinfo findByHouseholderId(@Param("id") int householderId);
	
}
