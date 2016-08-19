package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmEntrepreneurShip;
import com.nateiot.core.repository.BaseDao;

/**
 * 创业商户
 * 
 * @author Administrator
 *
 */
public interface CisBmEntrepreneurShipDao extends BaseDao<CisBmEntrepreneurShip, Integer>,
         CisBmEntrepreneurShipDaoPlus {
	
	@Query("select c from CisBmEntrepreneurShip c where c.entrepreneurShipId in (:entrepreneurShipIds) " )
	public List<CisBmEntrepreneurShip> queryListById(@Param("entrepreneurShipIds")List<Integer> entrepreneurShipIds);

}
