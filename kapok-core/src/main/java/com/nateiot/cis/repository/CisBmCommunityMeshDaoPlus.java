package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangweiming
 *
 */
public interface CisBmCommunityMeshDaoPlus {
	
	public List<Map<String, Object>> getUserAllMesh(Integer userId);
	
	public List<Map<String, Object>> getMeshChildren(Integer meshId);
	
	public Map<String, Object> count(String meshIds);

}
