package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisPaActivity;
import com.nateiot.cis.repository.CisPaActivityDao;
import com.nateiot.core.service.BaseService;

public interface CisPaActivityService extends BaseService<CisPaActivityDao, CisPaActivity, Integer>{

	public Map<String, Object> softDelList(List<Integer> ids);
}
