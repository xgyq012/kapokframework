package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 服务办事 -- 事件类型管理
 * 
 *  @author Guohw
 */
public interface CisSwEventTypeDaoPlus {
	
	Page<Map<String, Object>> findTheToDoList(Map<String, SearchFilter> conditions, Pageable pageable, Integer userId);

}
