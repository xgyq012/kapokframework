package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 服务办事 -- 民情日记
 * 
 *  @author Guohw
 */
public interface CisEsReportStatisticsDaoPlus {
	
	/**
	 * 民情日记统计 
	 */
	public Page<Map<String, Object>> diarySearch(String timeGte, String timeLte, 
			Map<String, SearchFilter> conditions, Pageable pageable, String first, String last);
	
	/**
	 * 积分统计 
	 */
	public Page<Map<String, Object>> scoreSearch(String timeGte, String timeLte, 
			Map<String, SearchFilter> conditions, Pageable pageable, String first, String last);

}
