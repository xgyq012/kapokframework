package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmShop;
import com.nateiot.cis.domain.CisBmWomanMsg;
import com.nateiot.core.repository.BaseDao;

public interface CisBmWomanMsgDao extends BaseDao<CisBmWomanMsg, Integer> , CisBmWomanMsgDaoPlus {
	
	@Query("select c from  CisBmWomanMsg c where c.womanId in (:ids) " )
	public List<CisBmWomanMsg> queryListById(@Param("ids")List<Integer> ids);
	
}
