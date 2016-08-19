package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmDrugGrant;
import com.nateiot.cis.repository.CisBmDrugGrantDao;
import com.nateiot.cis.service.CisBmDrugGrantService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmDrugGrantService")
@Transactional
public class CisBmDrugGrantServiceImpl  extends BaseServiceImpl<CisBmDrugGrantDao, CisBmDrugGrant, Integer>
			implements CisBmDrugGrantService {

	@Autowired
	public CisBmDrugGrantServiceImpl(CisBmDrugGrantDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmDrugGrantDao cisBmDrugGrantDao;

}
