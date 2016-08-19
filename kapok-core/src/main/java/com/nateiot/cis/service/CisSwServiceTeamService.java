package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisSwServiceTeam;
import com.nateiot.cis.repository.CisSwServiceTeamDao;
import com.nateiot.core.service.BaseService;

/**
 * 服务办事 -- 服务团队
 * 
 *  @author Guohw
 */

public interface CisSwServiceTeamService 
			extends BaseService<CisSwServiceTeamDao, CisSwServiceTeam, Integer>{
	
	/**
	 * 详细
	 */
//	public Map<String, Object> teamSelect(Integer teamId);

	/**
	 * 保存 
	 */
	public Map<String, Object> teamSave(CisSwServiceTeam cisSwServiceTeam, String root);
}
