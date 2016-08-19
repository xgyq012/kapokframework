package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.cis.repository.CisBmHouseholderDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

public interface CisBmHouseholderService extends BaseService<CisBmHouseholderDao, CisBmHouseholder, Integer> {
	
	public Map<String, Object> softDel(Integer id);
	
	public Map<String, Object> softDel(List<Integer> ids);
	
	public Map<String, Object> getNotHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable);
	
	public Map<String, Object> getCountResult(String meshIds);
}
