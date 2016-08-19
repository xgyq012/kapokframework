package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwFwsjClmx;
import com.nateiot.cis.repository.CisSwFwsjClmxDao;
import com.nateiot.cis.service.CisSwFwsjClmxService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 服务事件处理明细
 * @author xiewenhua
 *
 */
@Service("CisSwFwsjClmxService")
@Transactional
public class CisSwFwsjClmxServiceImpl extends
    BaseServiceImpl<CisSwFwsjClmxDao, CisSwFwsjClmx, Integer> implements CisSwFwsjClmxService {

	@Autowired
	private CisSwFwsjClmxDao cisSwFwsjClmxDao;
	
	@Autowired
	public CisSwFwsjClmxServiceImpl(CisSwFwsjClmxDao d) {
		super(d);
	}


}
