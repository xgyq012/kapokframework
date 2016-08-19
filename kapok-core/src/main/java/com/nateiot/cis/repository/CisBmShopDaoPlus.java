package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 门店信息
 * 
 * @author Administrator
 *
 */
public interface CisBmShopDaoPlus {
	
	public  Page<Map<String, Object>>  queryShop(Map<String, SearchFilter> conditions, Pageable pageable);
	
}
