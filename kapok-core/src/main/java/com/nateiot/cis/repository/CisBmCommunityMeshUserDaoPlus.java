package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangweiming
 *
 */
public interface CisBmCommunityMeshUserDaoPlus {

	public List<Map<String, Object>> findTonshi(Integer userId);
	
	public List<Map<String, Object>> findLindao(Integer userId);
}
