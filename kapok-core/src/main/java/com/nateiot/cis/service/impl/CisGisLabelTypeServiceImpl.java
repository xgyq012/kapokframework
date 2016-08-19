package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisGisLabelType;
import com.nateiot.cis.repository.CisGisLabelTypeDao;
import com.nateiot.cis.service.CisGisLabelTypeService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisGisLabelTypeService")
@Transactional
public class CisGisLabelTypeServiceImpl  extends 
		BaseServiceImpl<CisGisLabelTypeDao, CisGisLabelType, Integer> implements CisGisLabelTypeService {

	@Autowired
	public CisGisLabelTypeServiceImpl(CisGisLabelTypeDao d) {
		super(d);
	}
	
	@Autowired
	private CisGisLabelTypeDao cisGisLabelTypeDao ;

}
