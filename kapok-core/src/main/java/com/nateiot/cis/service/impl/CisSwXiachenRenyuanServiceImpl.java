package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwXiachenRenyuan;
import com.nateiot.cis.repository.CisSwXiachenRenyuanDao;
import com.nateiot.cis.service.CisSwXiachenRenyuanService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisSwXiachenRenyuanService")
@Transactional
public class CisSwXiachenRenyuanServiceImpl extends 
    BaseServiceImpl<CisSwXiachenRenyuanDao, CisSwXiachenRenyuan, Integer> implements 
    CisSwXiachenRenyuanService {
  
	@Autowired
	private CisSwXiachenRenyuanDao cisSwXiachenRenyuanDao;
	
	@Autowired
	public CisSwXiachenRenyuanServiceImpl(CisSwXiachenRenyuanDao d) {
		super(d);
	}


}
