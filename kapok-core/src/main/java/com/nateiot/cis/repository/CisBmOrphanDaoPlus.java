package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

public interface CisBmOrphanDaoPlus  {
	
	public Page<Map<String, Object>> getOrphanHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable);

}
