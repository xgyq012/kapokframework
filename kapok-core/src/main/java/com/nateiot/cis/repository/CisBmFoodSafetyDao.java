package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmFoodSafety;
import com.nateiot.core.repository.BaseDao;

/**
 * 食品安全
 * 
 * @author Administrator
 *
 */
public interface CisBmFoodSafetyDao extends BaseDao<CisBmFoodSafety, Integer>,
         CisBmFoodSafetyDaoPlus {
	
	@Query("select c from CisBmFoodSafety c where c.safoodsId in (:safoodsIds) " )
	public List<CisBmFoodSafety> queryListById(@Param("safoodsIds")List<Integer> safoodsIds);

}
