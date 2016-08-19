package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

public interface CisBmHouseMsgDaoPlus {
	
	public  Page<Map<String, Object>> selectHouse(Map<String, SearchFilter> conditions, Pageable pageable);
	
	public List<Map<String,Object>> getExistsHouseForHolder(Integer houseId);
	
	public  Page<Map<String, Object>>  queryHouseByHolder(Map<String, SearchFilter> conditions, Pageable pageable);

	public  Page<Map<String, Object>>  queryHouseByHolderLikeArgs(Map<String, SearchFilter> conditions, Pageable pageable,String args,String meshId);
	
}
