package com.nateiot.cis.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 党务建设 -- 两委分工
 * 
 *  @author Guohw
 */
public interface CisPaDivisionLaborDaoPlus {
	
	/**
	 * 查找党员 
	 */
	public Page<Map<String, Object>> findMember(Map<String, SearchFilter> conditions, Pageable pageable);

}
