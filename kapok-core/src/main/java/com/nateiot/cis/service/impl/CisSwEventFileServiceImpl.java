package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwEventFile;
import com.nateiot.cis.repository.CisSwEventFileDao;
import com.nateiot.cis.service.CisSwEventFileService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 服务办事 -- 事件登记文件上传
 * 
 *  @author Guohw
 */
@Service(value = "cisSwEventFileService")
@Transactional(readOnly=true)
public class CisSwEventFileServiceImpl 
		extends BaseServiceImpl<CisSwEventFileDao, CisSwEventFile, Integer>
		implements CisSwEventFileService{

	@Autowired
	private CisSwEventFileDao cisSwEventFileDao;
	
	@Autowired
	public CisSwEventFileServiceImpl(CisSwEventFileDao d) {
		super(d);
	}

}
