package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCommunityMesh;
import com.nateiot.cis.domain.CisBmCommunityMeshUser;
import com.nateiot.cis.repository.CisBmCommunityMeshDao;
import com.nateiot.cis.repository.CisBmCommunityMeshUserDao;
import com.nateiot.cis.service.CisBmCommunityMeshService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * @author zhangweiming
 *
 */
@Service(value = "cisBmCommunityMeshService")
@Transactional(readOnly = true)
public class CisBmCommunityMeshServiceImpl extends
		BaseServiceImpl<CisBmCommunityMeshDao, CisBmCommunityMesh, Integer>
		implements CisBmCommunityMeshService {

	@Autowired
	private CisBmCommunityMeshDao meshDao;
	
	@Autowired
	private CisBmCommunityMeshUserDao meshUserDao;

	@Autowired
	public CisBmCommunityMeshServiceImpl(
			CisBmCommunityMeshDao cisBmCommunityMeshDao) {
		super(cisBmCommunityMeshDao);
	}

	@Override
	@Transactional
	public Map<String, Object> doSave(CisBmCommunityMesh mesh) {
		resetResultMap();
		try {
			// 如果父节点的isleaf为Y修改为N
			Integer parentMeshId = mesh.getParentMeshId();
			if (parentMeshId != null) {
				CisBmCommunityMesh parentMesh = meshDao.findOne(parentMeshId);
				if (parentMesh != null
						&& StringUtils.equalsIgnoreCase("Y",
								parentMesh.getIsLeaf())) {
					parentMesh.setIsLeaf("N");
				}
			}
			// 如果isLeaf为空,则设置默认为Y
			if (StringUtils.isEmpty(mesh.getIsLeaf())) {
				mesh.setIsLeaf("Y");
			}
			meshDao.save(mesh);
			mesh.setFullPath(getFullpath(mesh.getMeshId()));
			resultMap.put(RESULT_ROW, meshDao.save(mesh));
			setResultStatus(0, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

	@Override
	public List<CisBmCommunityMesh> getMeshByParenMeshId(Integer parentMeshId) {
		return parentMeshId == 0 ? meshDao
				.findByParentMeshIdIsNullOrderByMeshIdAsc() : meshDao
				.findByParentMeshIdOrderByMeshIdAsc(parentMeshId);
	}

	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer meshId) {
		resetResultMap();
		try {
			Integer parentMeshId = meshDao.findOne(meshId).getParentMeshId();
			meshDao.delete(meshId);
			if (parentMeshId != null) {
				if (getMeshByParenMeshId(parentMeshId).size() == 0) {
					CisBmCommunityMesh parentMesh = meshDao
							.findOne(parentMeshId);
					parentMesh.setIsLeaf("Y");
				}
			}
			setResultStatus(0, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getUserMesh(Integer userId) {
		resetResultMap();
		try {
			List<CisBmCommunityMeshUser> meshUser = meshUserDao.findByUserUserId(userId);
			if (meshUser.size() == 1) {
				StringBuffer sb = new StringBuffer();
				for (Map<String, Object> child : meshDao.getMeshChildren(meshUser.get(0).getMeshId())) {
					sb.append(",").append(child.get("meshId"));
				}
				resultMap.put(RESULT_ROW, meshUser.get(0));
				resultMap.put("meshChildrenIds", sb.substring(1));
			}
			resultMap.put(RESULT_TOTAL, meshUser.size());
			setResultStatus(0, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getUserAllMesh(Integer userId) {
		resetResultMap();
		try {
			resultMap.put(RESULT_ROWS, meshDao.getUserAllMesh(userId));
			setResultStatus(0, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getMeshChildren(Integer meshId) {
		resetResultMap();
		try {
			resultMap.put(RESULT_ROWS, meshDao.getMeshChildren(meshId));
			setResultStatus(0, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
		}
		return resultMap;
	}
	
	private String getFullpath(Integer id) {
		CisBmCommunityMesh mesh = meshDao.findOne(id);
		if (mesh.getParentMeshId() != null) {
			return getFullpath(mesh.getParentMeshId()) + mesh.getMeshId() + ".";
		}
		return mesh.getMeshId() + ".";
	}

	@Override
	public Map<String, Object> count(String meshIds) {
		return meshDao.count(meshIds);
	}
	
 
}
