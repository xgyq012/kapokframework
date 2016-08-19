package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

public interface CisBmSqjzdxDaoPlus {

	public Page<Map<String, Object>> getSqjzHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable);
}
