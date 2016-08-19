package com.nateiot.base.repository;

import com.nateiot.base.domain.GxwlSysResource;
import com.nateiot.core.repository.BaseDao;

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
