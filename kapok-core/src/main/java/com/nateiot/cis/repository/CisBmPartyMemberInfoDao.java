package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmPartyMemberInfo;
import com.nateiot.core.repository.BaseDao;

public interface CisBmPartyMemberInfoDao extends BaseDao<CisBmPartyMemberInfo,Integer> , CisBmPartyMemberInfoDaoPlus {
	
	@Query("select c from  CisBmPartyMemberInfo c where c.partyId in (:ids) " )
	public List<CisBmPartyMemberInfo> queryListById(@Param("ids")List<Integer> ids);
	
	public CisBmPartyMemberInfo findByHouseholderId(Integer id);
	
}
