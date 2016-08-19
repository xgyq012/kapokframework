package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 应急管理 -- 避难场所
 * 
 * @author Administrator
 *
 */
public interface CisEmEmePlaceDaoPlus {

	public  Page<Map<String, Object>>  queryRefuge(Map<String, SearchFilter> conditions, Pageable pageable);
}
