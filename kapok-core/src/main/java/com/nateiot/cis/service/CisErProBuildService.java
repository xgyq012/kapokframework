package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisErProBuild;
import com.nateiot.cis.repository.CisErProBuildDao;
import com.nateiot.core.service.BaseService;

/**
 * 经济运行 -- 项目建设
 * 
 *  @author guohuawen
 */
public interface CisErProBuildService extends 
		BaseService<CisErProBuildDao, CisErProBuild, Integer>{
	
	/**
	 * 软删除 
	 */
	public Map<String, Object> softDel(Integer proBuildId);

}
