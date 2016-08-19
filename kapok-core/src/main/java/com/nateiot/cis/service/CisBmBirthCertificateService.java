package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmBirthCertificate;
import com.nateiot.cis.repository.CisBmBirthCertificateDao;
import com.nateiot.core.service.BaseService;

public interface CisBmBirthCertificateService  extends BaseService<CisBmBirthCertificateDao, CisBmBirthCertificate, Integer> {
	
	public Map<String, Object> delList(List<Integer> ids);

}
