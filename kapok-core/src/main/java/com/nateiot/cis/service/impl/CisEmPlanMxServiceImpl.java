package com.nateiot.cis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEmPlanMx;
import com.nateiot.cis.repository.CisEmPlanMxDao;
import com.nateiot.cis.service.CisEmPlanMxService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisEmPlanMxService")
@Transactional
public class CisEmPlanMxServiceImpl extends BaseServiceImpl<CisEmPlanMxDao, CisEmPlanMx, Integer> implements CisEmPlanMxService{

	@Autowired
	private CisEmPlanMxDao cisEmPlanMxDao;
	
	@Autowired
	public CisEmPlanMxServiceImpl(CisEmPlanMxDao d) {
		super(d);
	}

	@Override
	public Map<String, Object> findByYingjiPlanId(Integer yingjiPlanId) {
		Map<String, Object> resultList = new HashMap<String, Object>();
		resultList.put(RESULT_CODE, 0);
		resultList.put(RESULT_MSG, "查询成功！");
		resultList.put(RESULT_ROWS, cisEmPlanMxDao.findByYingjiPlanId(yingjiPlanId));
		return resultList;
	}

	@Override
	public Map<String, Object> doSave(CisEmPlanMx entity) {
		List<CisEmPlanMx> list = cisEmPlanMxDao.findByYingjiPlanId(entity.getYingjiPlanId());
		if(list.isEmpty()){
			entity.setNumber(1);
		}else{
		    if(entity.getPlanMxId() == null ){
				entity.setNumber(getMaxNumber(list) + 1);
		    }
		}
		return super.doSave(entity);
	}
	
	private int getMaxNumber(List<CisEmPlanMx> list){
		int maxNumber = 0;
	    for(CisEmPlanMx mx : list){
		    if(maxNumber < mx.getNumber()){
			    maxNumber = mx.getNumber();
		    }
	    }
	    return maxNumber;
	}
	

}
