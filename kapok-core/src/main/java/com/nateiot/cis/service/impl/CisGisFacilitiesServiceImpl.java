package com.nateiot.cis.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisGisPublicFacilities;
import com.nateiot.cis.repository.CisGisFacilitiesDao;
import com.nateiot.cis.service.CisGisFacilitiesService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Transactional
@Service(value="cisGisFacilitiesService")
public class CisGisFacilitiesServiceImpl extends BaseServiceImpl<CisGisFacilitiesDao, 
CisGisPublicFacilities, Integer> implements CisGisFacilitiesService {
	
	@Autowired
	public CisGisFacilitiesServiceImpl(CisGisFacilitiesDao d) {
		super(d);
	}

	@Autowired
	private CisGisFacilitiesDao cisGisFacilitiesDao;
}
