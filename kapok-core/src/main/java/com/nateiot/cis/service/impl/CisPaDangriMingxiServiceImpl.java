package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nateiot.cis.domain.CisPaDangriMingxi;
import com.nateiot.cis.repository.CisPaDangriMingxiDao;
import com.nateiot.cis.service.CisPaDangriMingxiService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisPaDangriMingxiService")
public class CisPaDangriMingxiServiceImpl extends BaseServiceImpl<CisPaDangriMingxiDao, CisPaDangriMingxi, Integer> 
	implements CisPaDangriMingxiService{

	@Autowired
	private CisPaDangriMingxiDao cisPaDangriMingxiDao;
	
	@Autowired
	public CisPaDangriMingxiServiceImpl(CisPaDangriMingxiDao d) {
		super(d);
	}

}
