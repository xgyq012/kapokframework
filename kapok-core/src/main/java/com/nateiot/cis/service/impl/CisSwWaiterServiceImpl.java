package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwWaiter;
import com.nateiot.cis.repository.CisSwWaiterDao;
import com.nateiot.cis.service.CisSwWaiterService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 服务团队人员信息
 * @author xiewenhua
 *
 */
@Service(value = "CisSwWaiterService")
@Transactional
public class CisSwWaiterServiceImpl extends 
    BaseServiceImpl<CisSwWaiterDao, CisSwWaiter, Integer> implements CisSwWaiterService {

	@Autowired
	private CisSwWaiterDao cisSwWaiterDao;
	
	@Autowired
	public CisSwWaiterServiceImpl(CisSwWaiterDao d) {
		super(d);
	}

}
