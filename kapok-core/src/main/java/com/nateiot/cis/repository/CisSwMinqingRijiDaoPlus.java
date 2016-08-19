package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.BaseDaoPlus;

public interface CisSwMinqingRijiDaoPlus extends BaseDaoPlus {
	public Page<Map<String, Object>>  doSearchBySql (Map<String, SearchFilter> conditions, Pageable pageable);
}
