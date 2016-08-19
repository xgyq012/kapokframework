package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmGuildOrganInfo;
import com.nateiot.core.repository.BaseDao;

public interface CisBmGuildOrganInfoDao extends BaseDao<CisBmGuildOrganInfo, Integer> , CisBmGuildOrganInfoDaoPlus {
	
	@Query("select c from  CisBmGuildOrganInfo c where c.organizationId in (:ids) " )
	public List<CisBmGuildOrganInfo> queryListById(@Param("ids")List<Integer> ids);
	
}
