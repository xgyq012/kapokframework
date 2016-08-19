package com.nateiot.cis.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;


/**
 * 考核督办 -- 积分明细记录
 * 
 *  @author Guohw
 */
public interface CisEsIntegalDetailDaoPlus {
	
	/**
	 * 查询  
	 */
	public Page<Map<String, Object>> searchDetail(String timeGte, 
			String timeLte, Map<String, SearchFilter> conditions, Pageable pageable);
	
	/**
	 * 查询用户当天登录次数 
	 */
	public List<Map<String, Object>> searchLoginTimes(Integer userId);

}
