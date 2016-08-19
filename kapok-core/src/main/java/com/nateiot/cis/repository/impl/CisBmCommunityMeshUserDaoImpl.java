package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.repository.CisBmCommunityMeshUserDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 * @author zhangweiming
 *
 */
public class CisBmCommunityMeshUserDaoImpl extends BaseDaoImpl implements CisBmCommunityMeshUserDaoPlus {

	@Override
	public List<Map<String, Object>> findTonshi(Integer userId) {
		String sql = "SELECT a.USER_ID AS userId"
				+" FROM cis_bm_community_mesh_user a "
				+" WHERE  a.MESH_ID IN (SELECT MESH_ID FROM cis_bm_community_mesh_user t WHERE t.USER_ID = " + userId + ")";
				
		return super.selectBySql(sql);
	}

	@Override
	public List<Map<String, Object>> findLindao(Integer userId) {
		String sql = "SELECT a.USER_ID AS userId"
					+" FROM cis_bm_community_mesh_user a"
					+" WHERE a.is_manager = 'Y' "
					+" AND a.MESH_ID IN (SELECT MESH_ID FROM cis_bm_community_mesh_user t WHERE t.USER_ID = "+ userId + ")";
		return super.selectBySql(sql);
	}
	
	
}
