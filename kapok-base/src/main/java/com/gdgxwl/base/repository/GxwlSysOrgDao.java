package com.gdgxwl.base.repository;

import com.gdgxwl.base.domain.GxwlSysOrg;
import com.gdgxwl.core.repository.BaseDao;

import java.util.List;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysOrgDao extends BaseDao<GxwlSysOrg, Integer>,
		GxwlSysOrgDaoPlus {

	public List<GxwlSysOrg> findByParentOrgIdIsNullOrderByIdAsc();

	public List<GxwlSysOrg> findByParentOrgIdOrderByIdAsc(Integer parentOrgId);
}
