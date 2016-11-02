package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.domain.GxwlSysMsgReciever;
import com.gdgxwl.base.repository.GxwlSysMsgRecieverDao;
import com.gdgxwl.base.service.GxwlSysMsgServiceReciever;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
