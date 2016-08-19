package com.nateiot.base.service;

import java.util.List;
import java.util.Map;

import com.nateiot.base.domain.GxwlSysOrg;
import com.nateiot.base.repository.GxwlSysOrgDao;
import com.nateiot.core.service.BaseService;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysOrgService extends
		BaseService<GxwlSysOrgDao, GxwlSysOrg, Integer> {

	public List<GxwlSysOrg> getGxwlSysOrgByParenOrgId(Integer parenOrgId);
	
	public Map<String, Object> findSubDataByOrgId(Integer orgId);
	
}
