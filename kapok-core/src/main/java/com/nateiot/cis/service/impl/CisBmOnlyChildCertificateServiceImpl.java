package com.nateiot.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmOnlyChildCertificate;
import com.nateiot.cis.repository.CisBmOnlyChildCertificateDao;
import com.nateiot.cis.service.CisBmOnlyChildCertificateService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisBmOnlyChildCertificateService")
@Transactional
public class CisBmOnlyChildCertificateServiceImpl extends BaseServiceImpl<CisBmOnlyChildCertificateDao, CisBmOnlyChildCertificate, Integer> implements
CisBmOnlyChildCertificateService {

	@Autowired
	public CisBmOnlyChildCertificateServiceImpl(CisBmOnlyChildCertificateDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmOnlyChildCertificateDao cisBmOnlyChildCertificateDao;

}
