package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCommunityMeshUser;
import com.nateiot.cis.repository.CisBmCommunityMeshUserDao;
import com.nateiot.cis.service.CisBmCommunityMeshUserService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * @author zhangweiming
 *
 */
@Service(value = "cisBmCommunityMeshUserService")
@Transactional(readOnly = true)
public class CisBmCommunityMeshUserServiceImpl
		extends
		BaseServiceImpl<CisBmCommunityMeshUserDao, CisBmCommunityMeshUser, Integer>
		implements CisBmCommunityMeshUserService {

	@Autowired
	private CisBmCommunityMeshUserDao meshUserDao;

	@Autowired
	public CisBmCommunityMeshUserServiceImpl(
			CisBmCommunityMeshUserDao cisBmCommunityMeshUserDao) {
		super(cisBmCommunityMeshUserDao);
	}

}
