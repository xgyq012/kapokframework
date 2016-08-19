package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisbmVolunteer;
import com.nateiot.cis.repository.CisbmVolunteerDao;
import com.nateiot.cis.service.CisbmVolunteerService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisbmVolunteerService")
@Transactional
public class CisbmVolunteerServiceImpl extends BaseServiceImpl<CisbmVolunteerDao, CisbmVolunteer, Integer> implements CisbmVolunteerService  {
	
	@Autowired
	public CisbmVolunteerServiceImpl(CisbmVolunteerDao d) {
		super(d);
	}

	@Autowired
	private CisbmVolunteerDao cisbmVolunteerDao;
	
}


