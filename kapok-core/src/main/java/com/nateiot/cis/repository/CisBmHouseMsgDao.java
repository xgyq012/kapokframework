package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmHouseMsg;
import com.nateiot.core.repository.BaseDao;

public interface CisBmHouseMsgDao extends BaseDao<CisBmHouseMsg, Integer> ,CisBmHouseMsgDaoPlus {
	
	@Query("select c from  CisBmHouseMsg c where c.houseId in (:ids) " )
	public List<CisBmHouseMsg> queryListById(@Param("ids")List<Integer> ids);
	
	@Query("select c from  CisBmHouseMsg c where c.cisBmBuildingMsg.buildId =:id " )
	public List<CisBmHouseMsg> queryListByBuildId(@Param("id")Integer id);
	
}
