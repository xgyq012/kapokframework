package com.nateiot.cis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmHealthinSuranceinfo;
import com.nateiot.cis.domain.CisBmSocialinfo;
import com.nateiot.core.repository.BaseDao;

/**
 * 医保信息
 * @author xiaguangjun
 *
 */
public interface CisBmHealthinSuranceinfoDao
		extends BaseDao<CisBmHealthinSuranceinfo, Integer>, CisBmHealthinSuranceinfoDaoPlus {
	
	@Query("select c from CisBmHealthinSuranceinfo c where c.householderId=:id and (c.delSign is null or c.delSign <> 'Y' ) ")
	public CisBmHealthinSuranceinfo findByHouseholderId(@Param("id") int householderId);
	
}
