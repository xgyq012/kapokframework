package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 党务建设 -- 党员设岗定责登记表
 * 
 * @author Guohw
 */
public interface CisPaPostDutyDaoPlus {
	
	/**
	 * 查找党员 
	 */
	public Page<Map<String, Object>> findMember(Map<String, SearchFilter> conditions, Pageable pageable);
	
	/**
	 * 查询 
	 */
	public Page<Map<String, Object>> searchBy(Map<String, SearchFilter> conditions, Pageable pageable);

}
