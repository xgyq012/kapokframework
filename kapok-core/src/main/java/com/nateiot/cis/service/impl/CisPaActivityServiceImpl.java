package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisPaActivity;
import com.nateiot.cis.repository.CisPaActivityDao;
import com.nateiot.cis.service.CisPaActivityService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisPaActivityService")
public class CisPaActivityServiceImpl extends BaseServiceImpl<CisPaActivityDao, CisPaActivity, Integer> implements CisPaActivityService{

	@Autowired
	private CisPaActivityDao cisPaActivityDao;
	
	@Autowired
	public CisPaActivityServiceImpl(CisPaActivityDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> ids) {
		try{
			resetResultMap();
			for(Integer id : ids){
				CisPaActivity act = cisPaActivityDao.findOne(id);
				act.setDelSign("Y");
				cisPaActivityDao.save(act);
			}
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错！");
		}
		return resultMap;
	}

}
