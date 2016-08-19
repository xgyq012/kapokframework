package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisEmEmePlace;
import com.nateiot.core.repository.BaseDao;

/**
 * 应急管理 -- 避难场所
 * 
 * @author Administrator
 *
 */
public interface CisEmEmePlaceDao extends BaseDao<CisEmEmePlace, Integer>,
		CisEmEmePlaceDaoPlus {

	@Query("select c from CisEmEmePlace c where c.emePlaceId in (:emePlaceIds) " )
	public List<CisEmEmePlace> queryListById(@Param("emePlaceIds")List<Integer> emePlaceIds);
}
