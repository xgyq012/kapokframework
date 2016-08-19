package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwRijiPingjia;
import com.nateiot.cis.repository.CisSwRijiPingjiaDao;
import com.nateiot.cis.service.CisSwRijiPingjiaService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 民情日记评价
 * @author xiewenhua
 *
 */
@Service("CisSwRijiPingjiaService")
@Transactional
public class CisSwRijiPingjiaServiceImpl extends BaseServiceImpl<CisSwRijiPingjiaDao, CisSwRijiPingjia, Integer> implements CisSwRijiPingjiaService {
	@Autowired
	private CisSwRijiPingjiaDao cisSwRijiPingjiaDao;

	@Autowired
	public CisSwRijiPingjiaServiceImpl(CisSwRijiPingjiaDao d) {
		super(d);
	}
	
}