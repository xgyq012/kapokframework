package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.nateiot.cis.domain.CisBmHolderRelationship;
import com.nateiot.cis.repository.CisBmHolderRelationshipDao;
import com.nateiot.core.service.BaseService;

public interface CisBmHolderRelationshipService extends BaseService<CisBmHolderRelationshipDao,CisBmHolderRelationship, Integer> {
	
	public Map<String,Object> searchAll(Specification<CisBmHolderRelationship> spec);
	
	public Map<String, Object> delList(List<Integer> ids);
	
}
