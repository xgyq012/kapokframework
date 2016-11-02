package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.common.SessionUtil;
import com.gdgxwl.base.domain.GxwlSysResource;
import com.gdgxwl.base.repository.GxwlSysResourceDao;
import com.gdgxwl.base.service.GxwlSysResourceService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysResourceService")
@Transactional(readOnly = true)
public class GxwlSysResourceServiceImpl extends
		BaseServiceImpl<GxwlSysResourceDao, GxwlSysResource, Integer> implements
		GxwlSysResourceService {

	@Autowired
	private GxwlSysResourceDao gxwlSysResourceDao;

	@Autowired
	public GxwlSysResourceServiceImpl(GxwlSysResourceDao gxwlSysResourceDao) {
		super(gxwlSysResourceDao);
	}

	@Override
	@Transactional
	public Map<String, Object> doSave(GxwlSysResource gxwlSysResource) {
		resetResultMap();
		try {
			// 如果父节点的isBranch为N修改为Y
			Integer parentResourceId = gxwlSysResource.getParentResourceId();
			if (parentResourceId != null) {
				GxwlSysResource parentResource = gxwlSysResourceDao
						.findOne(parentResourceId);
				if (parentResource != null
						&& "N".equalsIgnoreCase(parentResource.getIsBranch())) {
					parentResource.setIsBranch("Y");
					gxwlSysResourceDao.save(parentResource);
				}
			}

			// 如果isBranch为空,则设置默认为N
			if (StringUtils.isEmpty(gxwlSysResource.getIsBranch())) {
				gxwlSysResource.setIsBranch("N");
			}
			gxwlSysResource = gxwlSysResourceDao.save(gxwlSysResource);
			gxwlSysResource.setFullpath(getFullpath(gxwlSysResource.getId()));
			setResultStatus(0, "保存成功");
			resultMap.put(RESULT_ROW, gxwlSysResource);
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 1、先取出要删除资源的父资源ID。<br/>
	 * 2、删除该资源。<br/>
	 * 3、如果被删除资源有父资源ID，再判断该父资源是否有子资源。<br/>
	 * 4、如果没有子资源，则把父资源的isBranch属性修改为N。
	 * 
	 * @param id
	 *            要删除资源的id
	 */
	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer id) {
		resetResultMap();
		try {
			Integer parentResourceId = gxwlSysResourceDao.findOne(id)
					.getParentResourceId();
			gxwlSysResourceDao.delete(id);
			if (parentResourceId != null) {
				if (getGxwlSysResourceByParenResourceId(parentResourceId)
						.size() == 0) {
					GxwlSysResource parentResource = gxwlSysResourceDao
							.findOne(parentResourceId);
					parentResource.setIsBranch("N");
				}
			}
			setResultStatus(0, "删除成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public List<GxwlSysResource> getGxwlSysResourceByParenResourceId(
			Integer parenResourceId) {
		return parenResourceId == 0 ? gxwlSysResourceDao
				.findByParentResourceIdIsNullOrderBySeqAsc()
				: gxwlSysResourceDao
						.findByParentResourceIdOrderBySeqAsc(parenResourceId);
	}

	@Override
	public Map<String, Object> getCurrentUserMenuByParenResourceId(
			Integer parentResourceId) {
		resetResultMap();
		try {
			setResultStatus(0, "获取当前用户菜单成功");
			resultMap.put(RESULT_ROWS, gxwlSysResourceDao
					.getCurrentUserMenuByParenResourceId(SessionUtil
							.getCurrentUser().getUserId(), parentResourceId));
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取当前用户菜单时系统出错: " + e.getMessage());
		}
		return resultMap;
	}
	
	@Override
	public List<Map<String, Object>> getCurrentUserMenu() {
		return gxwlSysResourceDao.getCurrentUserMenu(SessionUtil.getCurrentUser().getUserId());
	}

	private String getFullpath(Integer id) {
		GxwlSysResource resource = gxwlSysResourceDao.findOne(id);
		if (resource.getParentResourceId() != null) {
			return getFullpath(resource.getParentResourceId())
					+ resource.getId() + ".";
		}
		return resource.getId() + ".";
	}

	@Override
	public Map<String, Object> getCurrentUserMenuByResourceType(
			String resourceType, String systemType) {
		resetResultMap();
		try {
			Integer userId = SessionUtil.getCurrentUser().getUserId();
			String resourceTypes = "'" + StringUtils.join(StringUtils.split(resourceType, ","), "','") + "'";
			if (StringUtils.isNotBlank(systemType)) {
				systemType = "'" + StringUtils.join(StringUtils.split(systemType, ","), "','") + "'";
			}
			resultMap.put(RESULT_ROWS, gxwlSysResourceDao
					.getCurrentUserMenuByResourceType(userId, resourceTypes, systemType));
			setResultStatus(0, "获取资源成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取资源时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getMenu() {
		 
		return super.doSearch();
	}
	
	@Override
	public List<Map<String, Object>> getCurrentUserMenuByModule(Integer menuId){
		
		return gxwlSysResourceDao.getCurrentUserMenuByModule(SessionUtil.getCurrentUser().getUserId(),menuId);
	}

	@Override
	public List<Map<String, Object>> getCurrentUserMenuByModuleParent(Integer menuId) {
		return gxwlSysResourceDao.getCurrentUserMenuByModuleParent(SessionUtil.getCurrentUser().getUserId(),menuId);
	}
 
}
