package com.nateiot.base.service;

import java.util.Map;

import com.nateiot.base.domain.GxwlSysRole;
import com.nateiot.base.repository.GxwlSysRoleDao;
import com.nateiot.core.service.BaseService;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysRoleService extends
		BaseService<GxwlSysRoleDao, GxwlSysRole, Integer> {

	public Map<String, Object> getGxwlSysResourceByRoleId(Integer roleId);

}
