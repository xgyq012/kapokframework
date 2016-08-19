package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisPaProperty;
import com.nateiot.cis.repository.CisPaPropertyDao;
import com.nateiot.core.service.BaseService;

public interface CisPaPropertyService extends BaseService<CisPaPropertyDao, CisPaProperty, Integer>{
	public Map<String, Object> softDelList(List<Integer> ids);
}
