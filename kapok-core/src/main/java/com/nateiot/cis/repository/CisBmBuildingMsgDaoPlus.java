package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

public interface CisBmBuildingMsgDaoPlus {
	public Page<Map<String, Object>>  search (Map<String, SearchFilter> conditions, Pageable pageable);
}
