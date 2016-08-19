package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisPaFinalDangri;
import com.nateiot.cis.repository.CisPaFinalDangriDao;
import com.nateiot.core.service.BaseService;

public interface CisPaFinalDangriService extends BaseService<CisPaFinalDangriDao, CisPaFinalDangri, Integer>{

	public Map<String, Object> softDelList(List<Integer> ids);
}
