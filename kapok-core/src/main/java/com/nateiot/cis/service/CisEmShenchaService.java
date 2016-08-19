package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisEmShencha;
import com.nateiot.cis.repository.CisEmShenchaDao;
import com.nateiot.core.service.BaseService;

public interface CisEmShenchaService extends BaseService<CisEmShenchaDao, CisEmShencha, Integer>{
	public Map<String, Object> findByYingjiShijianId(Integer yingjiShijianId);

}
