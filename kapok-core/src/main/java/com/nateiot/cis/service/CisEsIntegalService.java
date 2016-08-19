package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisEsIntegal;
import com.nateiot.cis.repository.CisEsIntegalDao;
import com.nateiot.core.service.BaseService;

/**
 * 考核督办 -- 积分管理
 * 
 * @author Administrator
 *
 */
public interface CisEsIntegalService extends
	BaseService<CisEsIntegalDao, CisEsIntegal, Integer> {
	
	public Map<String, Object> softDel(Integer integalId);
	
	public Map<String, Object> softDelList(List<Integer> integalIds);

	/**
	 * 通过积分配置编码，查找分值
	 * 
	 *  @param integalCode
	 *  @return
	 */
	public Integer findScore(String integalCode);
	
}
