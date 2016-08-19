package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

public interface CisBmPartyMemberInfoDaoPlus {

	/**
	 * 获取党员数据
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>>  search (Map<String, SearchFilter> conditions, Pageable pageable);
	
}
