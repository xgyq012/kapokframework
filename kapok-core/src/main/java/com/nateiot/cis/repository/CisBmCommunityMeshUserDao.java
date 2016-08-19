package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisBmCommunityMeshUser;
import com.nateiot.core.repository.BaseDao;

/**
 * @author zhangweiming
 *
 */
public interface CisBmCommunityMeshUserDao extends
		BaseDao<CisBmCommunityMeshUser, Integer>, CisBmCommunityMeshUserDaoPlus {

	public List<CisBmCommunityMeshUser> findByUserUserId(Integer userId);

}
