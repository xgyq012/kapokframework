package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmPartyOrganization;
import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.cis.repository.CisBmPartyOrganizationDao;
import com.nateiot.cis.service.CisBmPartyOrganizationService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisBmPartyOrganizationService")
@Transactional
public class CisBmPartyOrganizationServiceImpl extends BaseServiceImpl<CisBmPartyOrganizationDao, CisBmPartyOrganization, Integer> implements CisBmPartyOrganizationService  {
	
	@Autowired
	public CisBmPartyOrganizationServiceImpl(CisBmPartyOrganizationDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmPartyOrganizationDao cisBmPartyOrganizationDao;
	
	@Override
	@Transactional
	public Map<String, Object> delList(List<Integer> ids) {
	 
		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmPartyOrganization> list = cisBmPartyOrganizationDao.queryListById(ids);
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
