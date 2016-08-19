package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmUnits;
import com.nateiot.cis.repository.CisBmUnitsDao;
import com.nateiot.cis.service.CisBmUnitsService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 单位情况
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmUnitsService")
@Transactional(readOnly = true)
public class CisBmUnitsServiceImpl extends
         BaseServiceImpl<CisBmUnitsDao, CisBmUnits, Integer> implements
         CisBmUnitsService {
	
	@Autowired
	private CisBmUnitsDao cisBmUnitsDao;
	
	@Autowired
	public CisBmUnitsServiceImpl(CisBmUnitsDao cisBmUnitsDao) {
		super(cisBmUnitsDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer unitsId){
		resetResultMap();
		try{
			CisBmUnits bean = cisBmUnitsDao.findOne(unitsId);
			bean.setDelSign("N");
			cisBmUnitsDao.save(bean);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> count(String meshIds) {
		return cisBmUnitsDao.count(meshIds);
	}
	
}
