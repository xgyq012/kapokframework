package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisPaMemberPromise;
import com.nateiot.cis.repository.CisPaMemberPromiseDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 党务建设 -- 党员公开承诺活动登记表
 * 
 * @author Guohw
 */
public interface CisPaMemberPromiseService 
		extends BaseService<CisPaMemberPromiseDao, CisPaMemberPromise, Integer>{
	
	/**
	 * 软删除 
	 */
	public Map<String, Object> softDel(Integer promiseId);
	
	/**
	 * 查找党员 
	 */
	public Map<String, Object> findMember(Map<String, SearchFilter> conditions, Pageable pageable);
	
	/**
	 * 查询
	 * 
	 *  @param conditions, pageable
	 *  @return
	 */
	public Map<String, Object> searchBy(String timeGte, String timeLte, Map<String, SearchFilter> conditions, Pageable pageable);

}
