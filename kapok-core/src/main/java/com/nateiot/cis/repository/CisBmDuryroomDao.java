package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmDuryroomInfo;
import com.nateiot.core.repository.BaseDao;

/**
 * 值班室信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmDuryroomDao extends BaseDao<CisBmDuryroomInfo, Integer>,
		CisBmDuryroomDaoPlus {
	
	@Query("select c from CisBmDuryroomInfo c where c.duryroomId in (:duryroomIds) " )
	public List<CisBmDuryroomInfo> queryListById(@Param("duryroomIds")List<Integer> duryroomIds);

}
