package com.nateiot.cis.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.cis.domain.CisBmHolderRelationship;
import com.nateiot.cis.repository.CisBmHolderRelationshipDao;
import com.nateiot.cis.service.CisBmHolderRelationshipService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmHolderRelationshipService")
@Transactional
public class CisBmHolderRelationshipServiceImpl extends BaseServiceImpl<CisBmHolderRelationshipDao, 
					CisBmHolderRelationship, Integer> implements CisBmHolderRelationshipService {

	@Autowired
	private CisBmHolderRelationshipDao cisBmHolderRelationshipDao;
	
	@Autowired
	public CisBmHolderRelationshipServiceImpl(CisBmHolderRelationshipDao d) {
		super(d);
	}
	


	@Override
	public Map<String,Object> searchAll(Specification<CisBmHolderRelationship> spec){
		
		resetResultMap();
		
		List<CisBmHolderRelationship> list = cisBmHolderRelationshipDao.findAll(spec);
		
		resultMap.put(RESULT_CODE, 0);
		resultMap.put(RESULT_MSG, "查询成功。");
		resultMap.put(RESULT_ROWS,list);
		
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> delList(List<Integer> ids) {
		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmHolderRelationship> list = cisBmHolderRelationshipDao.queryListById(ids);
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
