package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmEntrepreneurStar;
import com.nateiot.core.repository.BaseDao;

/**
 * 单位信息
 * 
 * @author Administrator
 *
 */
public interface CisBmEntrepreneurStarDao extends BaseDao<CisBmEntrepreneurStar, Integer>,
         CisBmEntrepreneurStarDaoPlus {
	
	@Query("select c from CisBmEntrepreneurStar c where c.entrepreneurStarId in (:entrepreneurStarIds) " )
	public List<CisBmEntrepreneurStar> queryListById(@Param("entrepreneurStarIds")List<Integer> entrepreneurStarIds);

}
