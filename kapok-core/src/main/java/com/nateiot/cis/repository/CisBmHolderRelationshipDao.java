package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.cis.domain.CisBmHolderRelationship;
import com.nateiot.core.repository.BaseDao;

public interface CisBmHolderRelationshipDao extends BaseDao<CisBmHolderRelationship, Integer> , CisBmHolderRelationshipDaoPlus{

	@Query("select c from  CisBmHolderRelationship c where c.cisBmHouseholder.householderId =:householderId " )
	public CisBmHolderRelationship getCisBmHolderRelationshipByHouseHolderId(@Param("householderId")Integer householderId);
	
	@Query("select c from  CisBmHolderRelationship c where c.relationId in (:ids) " )
	public List<CisBmHolderRelationship> queryListById(@Param("ids")List<Integer> ids);


}
