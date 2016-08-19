package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;


/**
 * 党务建设 -- 社区党务公开表
 * 
 * @author Guohw
 */
public interface CisPaCommunityDutyDaoPlus {
	
	/**
	 * 查询 
	 */
	public Page<Map<String, Object>> searchBy(String timeGte, String timeLte, Map<String, SearchFilter> conditions, Pageable pageable);

}
