package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmCommunityMesh;
import com.nateiot.cis.repository.CisBmCommunityMeshDao;
import com.nateiot.core.service.BaseService;

/**
 * @author zhangweiming
 *
 */
public interface CisBmCommunityMeshService extends BaseService<CisBmCommunityMeshDao, CisBmCommunityMesh, Integer>  {

	
	public List<CisBmCommunityMesh> getMeshByParenMeshId(Integer parentMeshId);
	
	public Map<String, Object> getUserMesh(Integer userId);
	
	public Map<String, Object> getUserAllMesh(Integer userId);
	
	public Map<String, Object> getMeshChildren(Integer meshId);
	
	public Map<String, Object> count(String meshIds);
}
