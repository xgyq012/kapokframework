package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisBmCommunityMesh;
import com.nateiot.core.repository.BaseDao;

/**
 * @author zhangweiming
 *
 */
public interface CisBmCommunityMeshDao extends
		BaseDao<CisBmCommunityMesh, Integer>, CisBmCommunityMeshDaoPlus {
	
	public List<CisBmCommunityMesh> findByParentMeshIdIsNullOrderByMeshIdAsc();

	public List<CisBmCommunityMesh> findByParentMeshIdOrderByMeshIdAsc(Integer parentMeshId);
	
	public List<CisBmCommunityMesh> findByDelSign(String DelSign);

}
