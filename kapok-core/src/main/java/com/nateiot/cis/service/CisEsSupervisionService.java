package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisEsSupervision;
import com.nateiot.cis.repository.CisEsSupervisionDao;
import com.nateiot.core.service.BaseService;

/**
 * 考核督办 -- 事件督办
 * 
 * @author Administrator
 *
 */
public interface CisEsSupervisionService extends
	BaseService<CisEsSupervisionDao, CisEsSupervision, Integer> {
	
	public Map<String, Object> softDel(Integer supervisionId);
	
	public Map<String, Object> softDelList(List<Integer> supervisionIds);
	
}
