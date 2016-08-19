package com.nateiot.cis.service.impl;

import com.nateiot.cis.domain.CisBmRecruitment;
import com.nateiot.cis.repository.CisBmRecruitmentDao;
import com.nateiot.cis.service.CisBmRecruitmentService;
import com.nateiot.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单位招聘
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisBmRecruitmentService")
@Transactional(readOnly = true)
public class CisBmRecruitmentServiceImpl extends BaseServiceImpl<CisBmRecruitmentDao, CisBmRecruitment, Integer> implements CisBmRecruitmentService {
	
	@Autowired
	private CisBmRecruitmentDao cisBmRecruitmentDao;
	
	@Autowired
	public CisBmRecruitmentServiceImpl(CisBmRecruitmentDao cisBmRecruitmentDao) {
		super(cisBmRecruitmentDao);
	}
	

}
