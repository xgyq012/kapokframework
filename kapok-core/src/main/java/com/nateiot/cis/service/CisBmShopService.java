package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmShop;
import com.nateiot.cis.repository.CisBmShopDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 门店信息
 * 
 * @author Administrator
 *
 */
public interface CisBmShopService extends
	BaseService<CisBmShopDao, CisBmShop, Integer> {
	
	public Map<String, Object> softDel(Integer shopId);
	
	public Map<String, Object> delList(List<Integer> ids);
	
	public Map<String,Object>  queryShop(Map<String, SearchFilter> conditions, Pageable pageable);
	
}
