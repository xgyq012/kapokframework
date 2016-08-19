package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwEventSlave;
import com.nateiot.cis.repository.CisSwEventSlaveDao;
import com.nateiot.cis.service.CisSwEventSlaveService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 服务办事 -- 事件类型行表
 * 
 *  @author Guohw
 */
@Service(value = "cisSwEventSlaveService")
@Transactional(readOnly=true)
public class CisSwEventSlaveServiceImpl extends 
		BaseServiceImpl<CisSwEventSlaveDao, CisSwEventSlave, Integer>
		implements CisSwEventSlaveService{

	@Autowired
	private CisSwEventSlaveDao cisSwEventSlaveDao;
	
	@Autowired
	public CisSwEventSlaveServiceImpl(CisSwEventSlaveDao d) {
		super(d);
	}

}
