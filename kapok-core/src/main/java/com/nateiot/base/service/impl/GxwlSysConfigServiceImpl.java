package com.nateiot.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.domain.GxwlSysConfig;
import com.nateiot.base.repository.GxwlSysConfigDao;
import com.nateiot.base.service.GxwlSysConfigService;
import com.nateiot.core.service.BaseService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysConfigService")
@Transactional(readOnly = true)
public class GxwlSysConfigServiceImpl extends
		BaseServiceImpl<GxwlSysConfigDao, GxwlSysConfig, Integer> implements
		GxwlSysConfigService,
		BaseService<GxwlSysConfigDao, GxwlSysConfig, Integer> {

	@Autowired
	public GxwlSysConfigDao gxwlSysConfigDao;

	@Autowired
	public GxwlSysConfigServiceImpl(GxwlSysConfigDao gxwlSysConfigDao) {
		super(gxwlSysConfigDao);
	}


	
}
