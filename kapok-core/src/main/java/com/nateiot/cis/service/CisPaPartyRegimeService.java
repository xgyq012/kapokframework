package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisPaPartyRegime;
import com.nateiot.core.service.BaseService;
import com.nateiot.cis.repository.CisPaPartyRegimeDao;

/**
 * 党务建设 -- 党务公开制度
 * 
 * @author Guohw
 *
 */
public interface CisPaPartyRegimeService extends
	BaseService<CisPaPartyRegimeDao, CisPaPartyRegime, Integer> {
	
	public Map<String, Object> softDelList(List<Integer> regimeIds);
	
	public Map<String, Object> doSave(CisPaPartyRegime cisPaPartyRegime);
	
	public Map<String, Object> sendmail(String emailAddress, Integer regimeId);
	
	public Map<String, Object> sendmail2(String emailAddress, Integer regimeId);
	
	public Map<String, Object> getData();
	
}
