package com.nateiot.cis.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmMunicipalFacilite;
import com.nateiot.cis.repository.CisBmMunicipalFaciliteDao;
import com.nateiot.cis.service.CisBmMunicipalFaciliteService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmMunicipalFaciliteService")
@Transactional
public class CisBmMunicipalFaciliteServiceImpl extends BaseServiceImpl<CisBmMunicipalFaciliteDao, CisBmMunicipalFacilite, Integer> implements CisBmMunicipalFaciliteService  {

	@Autowired
	public CisBmMunicipalFaciliteServiceImpl(CisBmMunicipalFaciliteDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmMunicipalFaciliteDao cisBmMunicipalFaciliteDao;

}
