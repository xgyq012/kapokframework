package com.nateiot.cis.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCultivate;
import com.nateiot.cis.repository.CisBmCultivateDao;
import com.nateiot.cis.service.CisBmCultivateService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 劳动保障--培训
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisBmCultivateService")
@Transactional(readOnly = true)
public class CisBmCultivateServiceImpl extends
         BaseServiceImpl<CisBmCultivateDao, CisBmCultivate, Integer> implements
         CisBmCultivateService {
	
	@Autowired
	private CisBmCultivateDao cisBmCultivateDao;
	
	@Autowired
	public CisBmCultivateServiceImpl(CisBmCultivateDao cisBmCultivateDao) {
		super(cisBmCultivateDao);
	}
	
	
}
