package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmCommunityMsg;
import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.core.repository.BaseDao;

public interface CisBmCommunityMsgDao extends BaseDao<CisBmCommunityMsg,Integer>, CisBmCommunityMsgDaoPlus {
	
	@Query("select c from  CisBmCommunityMsg c where c.comId in (:ids) " )
	public List<CisBmCommunityMsg> queryListById(@Param("ids")List<Integer> ids);
	
}
