package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmPartyOrganization;
import com.nateiot.core.repository.BaseDao;

public interface CisBmPartyOrganizationDao extends BaseDao<CisBmPartyOrganization, Integer> , CisBmPartyOrganizationDaoPlus  {

	@Query("select c from  CisBmPartyOrganization c where c.organizationId in (:ids) " )
	public List<CisBmPartyOrganization> queryListById(@Param("ids")List<Integer> ids);
	
}
