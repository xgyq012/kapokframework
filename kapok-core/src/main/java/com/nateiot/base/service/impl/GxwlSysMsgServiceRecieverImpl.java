package com.nateiot.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.domain.GxwlSysMsgReciever;
import com.nateiot.base.repository.GxwlSysMsgRecieverDao;
import com.nateiot.base.service.GxwlSysMsgServiceReciever;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysMsgServiceReciever")
@Transactional(readOnly = true)
public class GxwlSysMsgServiceRecieverImpl extends
		BaseServiceImpl<GxwlSysMsgRecieverDao, GxwlSysMsgReciever, Integer>
		implements GxwlSysMsgServiceReciever {

	@Autowired
	private GxwlSysMsgRecieverDao gxwlSysMsgRecieverDao;

	@Autowired
	public GxwlSysMsgServiceRecieverImpl(
			GxwlSysMsgRecieverDao gxwlSysMsgRecieverDao) {
		super(gxwlSysMsgRecieverDao);
	}

}
