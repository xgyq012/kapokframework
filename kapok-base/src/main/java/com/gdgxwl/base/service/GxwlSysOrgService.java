package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysOrg;
import com.gdgxwl.base.repository.GxwlSysOrgDao;
import com.gdgxwl.core.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysOrgService extends
		BaseService<GxwlSysOrgDao, GxwlSysOrg, Integer> {

	public List<GxwlSysOrg> getGxwlSysOrgByParenOrgId(Integer parenOrgId);
	
	public Map<String, Object> findSubDataByOrgId(Integer orgId);
	
}
