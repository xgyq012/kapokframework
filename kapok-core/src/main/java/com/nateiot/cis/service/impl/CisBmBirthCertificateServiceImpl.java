package com.nateiot.cis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmBirthCertificate;
import com.nateiot.cis.domain.CisBmShop;
import com.nateiot.cis.repository.CisBmBirthCertificateDao;
import com.nateiot.cis.service.CisBmBirthCertificateService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Transactional
@Service("cisBmBirthCertificateService")
public class CisBmBirthCertificateServiceImpl extends BaseServiceImpl<CisBmBirthCertificateDao,CisBmBirthCertificate, Integer> implements CisBmBirthCertificateService  {

	@Autowired
	public CisBmBirthCertificateServiceImpl(CisBmBirthCertificateDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmBirthCertificateDao cisBmBirthCertificateDao;

	 

	@Override
	@Transactional
	public Map<String, Object> delList(List<Integer> ids) {

		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmBirthCertificate> list = cisBmBirthCertificateDao.queryListById(ids);
				resultMap = super.doDelete(list);
				resultMap.put(RESULT_MSG, "删除成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		
		return resultMap;
	}
	

}
