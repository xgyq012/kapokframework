package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisCcSms;
import com.nateiot.cis.repository.CisCcSmsDao;
import com.nateiot.cis.service.CisCcSmsService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 指挥中心 短信消息
 * @author xiewenhua
 *
 */
@Service(value = "CisCcSmsService")
@Transactional
public class CisCcSmsServiceImpl extends BaseServiceImpl<CisCcSmsDao, CisCcSms, Integer> implements CisCcSmsService{
	@Autowired
	private CisCcSmsDao cisCcSmsDao;

	@Autowired
	public CisCcSmsServiceImpl(CisCcSmsDao d) {
		super(d);
	}

}
