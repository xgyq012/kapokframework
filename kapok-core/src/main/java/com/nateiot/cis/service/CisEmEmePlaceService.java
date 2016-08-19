package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisEmEmePlace;
import com.nateiot.cis.repository.CisEmEmePlaceDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 应急管理 -- 避难场所
 * 
 * @author Administrator
 *
 */
public interface CisEmEmePlaceService extends
	BaseService<CisEmEmePlaceDao, CisEmEmePlace, Integer> {
	
//	public Map<String, Object> softDel(Integer emePlaceId);
	
	public Map<String, Object> softDelList(List<Integer> emePlaceIds);
	
	public  Map<String, Object>  queryRefuge(Map<String, SearchFilter> conditions, Pageable pageable);
}
