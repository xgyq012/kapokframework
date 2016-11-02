package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.domain.GxwlSysConfig;
import com.gdgxwl.base.repository.GxwlSysConfigDao;
import com.gdgxwl.base.service.GxwlSysConfigService;
import com.gdgxwl.core.service.BaseService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
