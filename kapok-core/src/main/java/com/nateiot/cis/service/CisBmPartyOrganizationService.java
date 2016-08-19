package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmPartyOrganization;
import com.nateiot.cis.repository.CisBmPartyOrganizationDao;
import com.nateiot.core.service.BaseService;

public interface CisBmPartyOrganizationService  extends
	BaseService<CisBmPartyOrganizationDao, CisBmPartyOrganization, Integer> {
	
	public Map<String, Object> delList(List<Integer> ids);

}
