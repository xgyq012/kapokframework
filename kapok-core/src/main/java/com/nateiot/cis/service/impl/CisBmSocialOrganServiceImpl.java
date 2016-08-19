package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmSocialOrgan;
import com.nateiot.cis.repository.CisBmSocialOrganDao;
import com.nateiot.cis.service.CisBmSocialOrganService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmSocialOrganService")
@Transactional
public class CisBmSocialOrganServiceImpl extends BaseServiceImpl<CisBmSocialOrganDao, CisBmSocialOrgan, Integer> implements CisBmSocialOrganService {

	@Autowired
	public CisBmSocialOrganServiceImpl(CisBmSocialOrganDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmSocialOrganDao cisBmSocialOrganDao;

}
