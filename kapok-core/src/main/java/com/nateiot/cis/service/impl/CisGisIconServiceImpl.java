package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisGisIcon;
import com.nateiot.cis.repository.CisGisIconDao;
import com.nateiot.cis.service.CisGisIconService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisGisIconService")
@Transactional
public class CisGisIconServiceImpl  extends 
		BaseServiceImpl<CisGisIconDao, CisGisIcon, Integer> implements CisGisIconService {

	@Autowired
	public CisGisIconServiceImpl(CisGisIconDao d) {
		super(d);
	}
	
	@Autowired
	private CisGisIconDao cisGisIconDao ;

}
