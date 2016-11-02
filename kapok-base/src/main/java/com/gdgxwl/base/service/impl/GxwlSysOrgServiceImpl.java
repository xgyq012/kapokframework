package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.domain.GxwlSysOrg;
import com.gdgxwl.base.domain.GxwlSysOrgRole;
import com.gdgxwl.base.domain.GxwlSysOrgUser;
import com.gdgxwl.base.repository.GxwlSysOrgDao;
import com.gdgxwl.base.repository.GxwlSysOrgRoleDao;
import com.gdgxwl.base.repository.GxwlSysOrgUserDao;
import com.gdgxwl.base.service.GxwlSysOrgService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysOrgService")
@Transactional(readOnly = true)
public class GxwlSysOrgServiceImpl extends
		BaseServiceImpl<GxwlSysOrgDao, GxwlSysOrg, Integer> implements
		GxwlSysOrgService {

	@Autowired
	private GxwlSysOrgDao gxwlSysOrgDao;
	
	@Autowired
	private GxwlSysOrgUserDao gxwlSysOrgUserDao;
	
	@Autowired
	private GxwlSysOrgRoleDao gxwlSysOrgRoleDao;

	@Autowired
	public GxwlSysOrgServiceImpl(GxwlSysOrgDao gxwlSysOrgDao) {
		super(gxwlSysOrgDao);
	}

	@Override
	@Transactional
	public Map<String, Object> doSave(GxwlSysOrg gxwlSysOrg) {
		resetResultMap();
		try {
			Integer orgId = gxwlSysOrg.getId();
			// 如果父节点的isleaf为Y修改为N
			Integer parentOrgId = gxwlSysOrg.getParentOrgId();
			if (parentOrgId != null) {
				GxwlSysOrg parentOrg = gxwlSysOrgDao.findOne(parentOrgId);
				if (parentOrg != null && "Y".equalsIgnoreCase(parentOrg.getIsleaf())) {
					parentOrg.setIsleaf("N");
					gxwlSysOrgDao.save(parentOrg);
				}
			}
			// 如果isleaf为空,则设置默认为Y
			if (StringUtils.isEmpty(gxwlSysOrg.getIsleaf())) {
				gxwlSysOrg.setIsleaf("Y");
			}
			if (orgId == null) {
				gxwlSysOrgDao.save(gxwlSysOrg);
				gxwlSysOrg.setFullpath(getFullpath(gxwlSysOrg.getId()));
				List<GxwlSysOrgUser> orgUsers = gxwlSysOrg.getOrgUsers();
				List<GxwlSysOrgRole> orgRoles = gxwlSysOrg.getOrgRoles();
				if (orgUsers != null) {
					for (GxwlSysOrgUser orgUser : orgUsers) {
						orgUser.setOrgId(gxwlSysOrg.getId());
					}
				}
				if (orgRoles != null) {
					for (GxwlSysOrgRole orgRole : orgRoles) {
						orgRole.setOrgId(gxwlSysOrg.getId());
					}
				}
				setResultStatus(0, "保存成功");
				resultMap.put(RESULT_ROW, gxwlSysOrg);
				return resultMap;
			} else {
				GxwlSysOrg org = gxwlSysOrgDao.findOne(orgId);
				List<GxwlSysOrgUser> oldOrgUsers = org.getOrgUsers();
				List<GxwlSysOrgUser> newOrgUsers = gxwlSysOrg.getOrgUsers();
				if (newOrgUsers == null) {
					gxwlSysOrgUserDao.deleteInBatch(oldOrgUsers);
				} else {
					for (GxwlSysOrgUser oldOrgUser : oldOrgUsers) {
						if (!newOrgUsers.contains(oldOrgUser)) {
							gxwlSysOrgUserDao.delete(oldOrgUser);
						}
					}
				}
				List<GxwlSysOrgRole> oldOrgRoles = org.getOrgRoles();
				List<GxwlSysOrgRole> newOrgRoles = gxwlSysOrg.getOrgRoles();
				if (newOrgRoles == null) {
					gxwlSysOrgRoleDao.deleteInBatch(oldOrgRoles);
				} else {
					for (GxwlSysOrgRole oldOrgRole : oldOrgRoles) {
						if (!newOrgRoles.contains(oldOrgRole)) {
							gxwlSysOrgRoleDao.delete(oldOrgRole);
						}
					}
				}
				setResultStatus(0, "保存成功");
				resultMap.put(RESULT_ROW, gxwlSysOrgDao.save(gxwlSysOrg));
				return resultMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
			return resultMap;
		}
	}
	
	@Override
	public List<GxwlSysOrg> getGxwlSysOrgByParenOrgId(Integer parenOrgId) {
		return parenOrgId == 0 ? 
				gxwlSysOrgDao.findByParentOrgIdIsNullOrderByIdAsc() : 
				gxwlSysOrgDao.findByParentOrgIdOrderByIdAsc(parenOrgId);
	}

	/**
	 * 1、先取出要删除组织的父组织ID。<br/>
	 * 2、删除该组织。<br/>
	 * 3、如果被删除组织有父组织ID，再判断该父组织是否有子组织。<br/>
	 * 4、如果没有子组织，则把父组织的isleaf属性修改为Y。
	 * 
	 * @param id 要删除组织的id
	 */
	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer id) {
		resetResultMap();
		try {
			Integer parentOrgId = gxwlSysOrgDao.findOne(id).getParentOrgId();
			gxwlSysOrgDao.delete(id);
			if (parentOrgId != null) {
				if (getGxwlSysOrgByParenOrgId(parentOrgId).size() == 0) {
					GxwlSysOrg parentOrg = gxwlSysOrgDao.findOne(parentOrgId);
					parentOrg.setIsleaf("Y");
				}
			}
			setResultStatus(0, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

	private String getFullpath(Integer id) {
		GxwlSysOrg org = gxwlSysOrgDao.findOne(id);
		if (org.getParentOrgId() != null) {
			return getFullpath(org.getParentOrgId()) + org.getId() + ".";
		}
		return org.getId() + ".";
	}

	@Override
	public Map<String, Object> findSubDataByOrgId(Integer orgId) {
		resetResultMap();
		try {
			Map<String, Object> row = new HashMap<String, Object>();
			setResultStatus(0, "查询成功");
			row.put("roles", gxwlSysOrgDao.findRoleByOrgId(orgId));
			row.put("users", gxwlSysOrgDao.findUserByOrgId(orgId));
			resultMap.put(RESULT_ROW, row);
			return resultMap;
		} catch (Exception e) {
			setResultStatus(-1, "查询时系统出错" + e.getMessage());
			return resultMap;
		}
	}

}
