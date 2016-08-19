package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.core.repository.BaseDao;

public interface CisBmHouseholderDao extends BaseDao<CisBmHouseholder, Integer> , CisBmHouseholderDaoPlus{
	
	@Query("select c from  CisBmHouseholder c where c.householderId in (:ids) " )
	public List<CisBmHouseholder> queryListById(@Param("ids")List<Integer> ids);
	
	
}
