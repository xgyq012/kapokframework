package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisGisMunicipalFacilities;
import com.nateiot.cis.repository.CisGisMunicipalFacilitiesDao;
import com.nateiot.cis.service.CisGisMunicipalFacilitiesService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Transactional
@Service(value="cisGisMunicipalFacilitiesService")
public class CisGisMunicipalFacilitiesServiceImpl  extends BaseServiceImpl<CisGisMunicipalFacilitiesDao, CisGisMunicipalFacilities, Integer> implements CisGisMunicipalFacilitiesService  {

	@Autowired
	public CisGisMunicipalFacilitiesServiceImpl(CisGisMunicipalFacilitiesDao d) {
		super(d);
	}

	@Autowired
	private CisGisMunicipalFacilitiesDao cisGisMunicipalFacilitiesDao;
	
}
