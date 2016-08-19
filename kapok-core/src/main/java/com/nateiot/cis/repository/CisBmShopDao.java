package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.cis.domain.CisBmShop;
import com.nateiot.core.repository.BaseDao;

/**
 * 门店信息
 * 
 * @author Administrator
 *
 */
public interface CisBmShopDao extends BaseDao<CisBmShop, Integer>,
		CisBmShopDaoPlus {
	
	@Query("select c from  CisBmShop c where c.shopId in (:ids) " )
	public List<CisBmShop> queryListById(@Param("ids")List<Integer> ids);

}
