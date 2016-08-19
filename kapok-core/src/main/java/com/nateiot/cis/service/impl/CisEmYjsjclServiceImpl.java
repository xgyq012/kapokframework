package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEmYjsjcl;
import com.nateiot.cis.repository.CisEmYjsjclDao;
import com.nateiot.cis.service.CisEmYjsjclService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 应急事件处理
 * @author xiewenhua
 *
 */
@Service(value = "CisEmYjsjclService")
@Transactional
public class CisEmYjsjclServiceImpl extends BaseServiceImpl<CisEmYjsjclDao, CisEmYjsjcl, Integer> implements CisEmYjsjclService {

	@Autowired
	private CisEmYjsjclDao cisEmYjsjclDao;
	
	@Autowired
	public CisEmYjsjclServiceImpl(CisEmYjsjclDao d) {
		super(d);
	}

}