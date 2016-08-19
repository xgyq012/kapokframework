package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 考核督办 -- 民情日记统计
 * 
 * @author Administrator
 *
 */
public interface CisEsModiaryDaoPlus {
	
	/**
	 *  民情日记统计
	 */
	public List<Map<String, Object>> moDiaryFormSearch(Map<String, SearchFilter> conditions);

}
