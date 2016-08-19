package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmHouseMsg;
import com.nateiot.cis.repository.CisBmHouseMsgDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmHouseMsgService extends BaseService<CisBmHouseMsgDao, CisBmHouseMsg, Integer> {
	
	public Map<String, Object> delList(List<Integer> ids);
	
	public Map<String, Object> softDel(List<Integer> ids);
	
	public Map<String, Object> softDel(Integer id);
	
	public Map<String,Object>  selectHouse(Map<String, SearchFilter> conditions, Pageable pageable);
	
	public Map<String,Object>  existsHouseByBuildId(Integer buildId);
	
	public  Map<String,Object>  updateHouseLonAndLat(Integer houseId,String lon,String lat);
	
	public Map<String,Object>  queryHouseByHolder(Map<String, SearchFilter> conditions, Pageable pageable);

	public Map<String,Object>  queryHouseByHolderLikeArgs(Pageable pageable,String args,String meshId);

}
