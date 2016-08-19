package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisEsOversee;
import com.nateiot.cis.domain.CisSwFuwuShijian;
import com.nateiot.cis.repository.CisEsOverseeDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 考核督办 -- 事件督办
 * 
 *  @author guohuawen
 */
public interface CisEsOverseeService extends 
		BaseService<CisEsOverseeDao, CisEsOversee, Integer>{
	
	/**
	 * 查询
	 */
	public Map<String, Object> enrollSearch(Map<String, SearchFilter> conditions, Pageable pageable);

	/**
	 * 详细
	 */
	public Map<String, Object> enrollSelect(Integer enrollId);
}
