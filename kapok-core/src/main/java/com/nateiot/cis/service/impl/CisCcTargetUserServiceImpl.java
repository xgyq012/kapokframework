package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nateiot.cis.domain.CisCcTargetUser;
import com.nateiot.cis.repository.CisCcNoticeDao;
import com.nateiot.cis.repository.CisCcTargetUserDao;
import com.nateiot.cis.service.CisCcNoticeService;
import com.nateiot.cis.service.CisCcTargetUserService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisCcTargetUserService")
public class CisCcTargetUserServiceImpl extends BaseServiceImpl<CisCcTargetUserDao, CisCcTargetUser, Integer> implements CisCcTargetUserService{

	@Autowired
	private CisCcTargetUserDao cisCcTargetUserDao;	
	@Autowired
	private CisCcNoticeDao cisCcNoticeDao; 
	@Autowired
	private CisCcNoticeService noticeService;
	
	@Autowired
	public CisCcTargetUserServiceImpl(CisCcTargetUserDao d) {
		super(d);
	}
}
