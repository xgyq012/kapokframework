package com.nateiot.cis.service.impl;

import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisBmJobRegistration;
import com.nateiot.cis.repository.CisBmJobRegistrationDao;
import com.nateiot.cis.service.CisBmJobRegistrationService;
import com.nateiot.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 求职登记
 * 
 * @author xiaguangjun
 *
 */
@Service(value = "cisBmJobRegistrationService")
@Transactional(readOnly = true)
public class CisBmJobRegistrationServiceImpl extends
         BaseServiceImpl<CisBmJobRegistrationDao, CisBmJobRegistration, Integer> implements
         CisBmJobRegistrationService {
	
	@Autowired
	private CisBmJobRegistrationDao cisBmJobRegistrationDao;
	
	@Autowired
	public CisBmJobRegistrationServiceImpl(CisBmJobRegistrationDao cisBmJobRegistrationDao) {
		super(cisBmJobRegistrationDao);
	}

	@Autowired
	public GxwlSysDocService  gxwlSysDocService;

	@Override
	@Transactional
	public Map<String, Object> doSave(CisBmJobRegistration entity) {


		if(entity!=null && entity.getDocId()!=null){
			gxwlSysDocService.moveDoc(entity.getDocId());
		}

		return super.doSave(entity);
	}

	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer integer) {
		CisBmJobRegistration model = cisBmJobRegistrationDao.findOne(integer);
		if(model!=null && model.getDocId()!=null){
			gxwlSysDocService.deleteFile(model.getDocId());
		}
		return super.doDelete(integer);
	}
}
