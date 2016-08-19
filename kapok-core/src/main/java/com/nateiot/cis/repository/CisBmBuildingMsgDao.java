package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.core.repository.BaseDao;

public interface CisBmBuildingMsgDao extends BaseDao<CisBmBuildingMsg,Integer> , CisBmBuildingMsgDaoPlus {
	
	@Query("select c from  CisBmBuildingMsg c where c.buildId in (:ids) " )
	public List<CisBmBuildingMsg> queryListById(@Param("ids")List<Integer> ids);

}
