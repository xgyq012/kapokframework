package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.cis.domain.CisBmInspectMsg;
import com.nateiot.core.repository.BaseDao;

public interface CisBmInspectMsgDao extends BaseDao<CisBmInspectMsg, Integer> , CisBmInspectMsgDaoPlus {

	@Query("select c from  CisBmInspectMsg c where c.inspectId in (:ids) " )
	public List<CisBmInspectMsg> queryListById(@Param("ids")List<Integer> ids);
	
}
