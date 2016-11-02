package com.gdgxwl.base.repository;

import com.gdgxwl.base.domain.GxwlSysResource;
import com.gdgxwl.core.repository.BaseDao;

import java.util.List;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysResourceDao extends BaseDao<GxwlSysResource, Integer>,
		GxwlSysResourceDaoPlus {

	public List<GxwlSysResource> findByParentResourceIdIsNullOrderBySeqAsc();

	public List<GxwlSysResource> findByParentResourceIdOrderBySeqAsc(Integer parentResourceId);

	public GxwlSysResource findByResourceCode(String resourceCode);

}
