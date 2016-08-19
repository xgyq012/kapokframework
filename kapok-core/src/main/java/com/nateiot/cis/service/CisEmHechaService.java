package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisEmHecha;
import com.nateiot.cis.repository.CisEmHechaDao;
import com.nateiot.core.service.BaseService;

public interface CisEmHechaService extends BaseService<CisEmHechaDao, CisEmHecha, Integer>{
	public Map<String, Object> findByYingjiShijianId(Integer yingjiShijianId);

}
