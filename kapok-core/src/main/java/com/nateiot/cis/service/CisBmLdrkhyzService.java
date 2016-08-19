package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmLdrkhyz;
import com.nateiot.cis.repository.CisBmLdrkhyzDao;
import com.nateiot.core.service.BaseService;

public interface CisBmLdrkhyzService extends BaseService<CisBmLdrkhyzDao, CisBmLdrkhyz, Integer> {
	
	public Map<String, Object> delList(List<Integer> ids);

}
