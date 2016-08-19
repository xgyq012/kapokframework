package com.nateiot.base.repository;

import java.util.List;

import com.nateiot.base.domain.GxwlSysOrg;
import com.nateiot.core.repository.BaseDao;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysOrgDao extends BaseDao<GxwlSysOrg, Integer>,
		GxwlSysOrgDaoPlus {

	public List<GxwlSysOrg> findByParentOrgIdIsNullOrderByIdAsc();

	public List<GxwlSysOrg> findByParentOrgIdOrderByIdAsc(Integer parentOrgId);
}
