package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisPaDivisionRowtable;
import com.nateiot.cis.repository.CisPaDivisionRowtableDao;
import com.nateiot.cis.service.CisPaDivisionRowtableService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 党务建设 -- 两委分工行表
 * 
 *  @author Guohw
 */
@Service(value = "CisPaDivisionRowtableService")
@Transactional(readOnly=true)
public class CisPaDivisionRowtableServiceImpl 
		extends BaseServiceImpl<CisPaDivisionRowtableDao, CisPaDivisionRowtable, Integer>
		implements CisPaDivisionRowtableService{
	
	@Autowired
	private CisPaDivisionRowtableDao cisPaDivisionRowtableDao;
	
	@Autowired
	public CisPaDivisionRowtableServiceImpl(CisPaDivisionRowtableDao d){
		super(d);
	}

}
