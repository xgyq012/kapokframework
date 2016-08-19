package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmFireControl;
import com.nateiot.core.repository.BaseDao;

/**
 * 消防信息
 * 
 * @author Administrator
 *
 */
public interface CisBmFireControlDao extends BaseDao<CisBmFireControl, Integer>,
         CisBmFireControlDaoPlus {
	
	@Query("select c from CisBmFireControl c where c.cofireId in (:cofireIds) " )
	public List<CisBmFireControl> queryListById(@Param("cofireIds")List<Integer> cofireIds);

}
