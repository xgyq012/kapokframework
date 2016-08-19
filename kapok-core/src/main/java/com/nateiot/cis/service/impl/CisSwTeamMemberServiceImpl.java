package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwTeamMember;
import com.nateiot.cis.repository.CisSwTeamMemberDao;
import com.nateiot.cis.service.CisSwTeamMemberService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 服务办事 -- 服务团队行表
 * 
 *  @author Guohw
 */
@Service(value = "cisSwTeamMemberService")
@Transactional(readOnly = true)
public class CisSwTeamMemberServiceImpl 
		extends BaseServiceImpl<CisSwTeamMemberDao, CisSwTeamMember, Integer>
		implements CisSwTeamMemberService{
	
	@Autowired
	private CisSwTeamMemberDao cisSwTeamMemberDao;
	
	@Autowired
	public CisSwTeamMemberServiceImpl(CisSwTeamMemberDao d) {
		super(d);
	}

}
