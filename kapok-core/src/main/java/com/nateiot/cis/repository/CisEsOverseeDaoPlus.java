package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 考核督办 -- 事件督办
 * 
 *  @author guohuawen
 */
public interface CisEsOverseeDaoPlus {
	
	/**
	 * 查询 
	 */
	public Page<Map<String, Object>> search(Map<String, SearchFilter> conditions, Pageable pageable);

}
