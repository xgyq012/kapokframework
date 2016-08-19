package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nateiot.cis.domain.CisPaPropertyDetails;
import com.nateiot.cis.repository.CisPaPropertyDetailsDao;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisPaPropertyDetailsService")
public class CisPaPropertyDetailsServiceImpl extends BaseServiceImpl<CisPaPropertyDetailsDao, CisPaPropertyDetails, Integer>{

	@Autowired
	private CisPaPropertyDetailsDao cisPaPropertyDetailsDao;
	
	@Autowired
	public CisPaPropertyDetailsServiceImpl(CisPaPropertyDetailsDao d) {
		super(d);
	}

}
