package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.domain.GxwlSysRole;
import com.gdgxwl.base.domain.GxwlSysRoleResource;
import com.gdgxwl.base.repository.GxwlSysRoleDao;
import com.gdgxwl.base.repository.GxwlSysRoleResourceDao;
import com.gdgxwl.base.service.GxwlSysRoleService;
import com.gdgxwl.core.service.BaseService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysRoleService")
@Transactional(readOnly = true)
public class GxwlSysRoleServiceImpl extends
		BaseServiceImpl<GxwlSysRoleDao, GxwlSysRole, Integer> implements
		GxwlSysRoleService, BaseService<GxwlSysRoleDao, GxwlSysRole, Integer> {

	@Autowired
	private GxwlSysRoleDao gxwlSysRoleDao;
	
	@Autowired
	private GxwlSysRoleResourceDao gxwlSysRoleResourceDao;

	@Autowired
	public GxwlSysRoleServiceImpl(GxwlSysRoleDao gxwlSysRoleDao) {
		super(gxwlSysRoleDao);
	}
	
	@Transactional
	@Override
	public Map<String, Object> doSave(GxwlSysRole gxwlSysRole) {
		resetResultMap();
		try {
			Integer roleId = gxwlSysRole.getRoleId();
			if (roleId == null) {
				gxwlSysRoleDao.save(gxwlSysRole);
				List<GxwlSysRoleResource> resources = gxwlSysRole.getResources();
				if (resources != null) {
					for (GxwlSysRoleResource resource : resources) {
						resource.setRoleId(gxwlSysRole.getRoleId());
					}
				}
				return super.doSave(gxwlSysRole);
			} else {
				GxwlSysRole r = gxwlSysRoleDao.findOne(roleId);
				List<GxwlSysRoleResource> olds = r.getResources();
				List<GxwlSysRoleResource> news = gxwlSysRole.getResources();
				if (news == null) {
					gxwlSysRoleResourceDao.deleteInBatch(olds);
				} else {
					for (GxwlSysRoleResource resource : olds) {
						if (!news.contains(resource)) {
							gxwlSysRoleResourceDao.delete(resource);
						}
					}
				}
				return super.doSave(gxwlSysRole);
			}
		} catch (Exception e) {
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
			return resultMap;
		}
	}
	
	@Transactional
	@Override
	public Map<String, Object> doDelete(Integer id) {
		resetResultMap();
		try {
			List<Map<String, Object>> users = gxwlSysRoleDao.getUsersByRoleId(id);
			if (CollectionUtils.isEmpty(users)) {
				return super.doDelete(id);
			} else {
				StringBuffer msg = new StringBuffer();
				for (Map<String, Object> user : users) {
					msg.append("," + user.get("realname").toString());
				}
				setResultStatus(1, "角色已经分配给以下用户：【" + msg.substring(1) + "】不能删除！");
				return resultMap;
			}
		} catch (Exception e) {
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getGxwlSysResourceByRoleId(Integer roleId) {
		try {
			List<Map<String,Object>> resources = gxwlSysRoleDao.getGxwlSysResourceByRoleId(roleId);
			resetResultMap();
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROWS, resources);
			return resultMap;
		} catch (Exception e) {
			resetResultMap();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

}
