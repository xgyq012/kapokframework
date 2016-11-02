package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysRole;
import com.gdgxwl.base.repository.GxwlSysRoleDao;
import com.gdgxwl.core.service.BaseService;

import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysRoleService extends
		BaseService<GxwlSysRoleDao, GxwlSysRole, Integer> {

	public Map<String, Object> getGxwlSysResourceByRoleId(Integer roleId);

}
