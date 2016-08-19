package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmPatrolInfo;
import com.nateiot.cis.repository.CisBmPatrolDao;
import com.nateiot.cis.service.CisBmPatrolService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 巡逻队信息
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmPatrolService")
@Transactional(readOnly = true)
public class CisBmPatrolServiceImpl extends
         BaseServiceImpl<CisBmPatrolDao, CisBmPatrolInfo, Integer> implements
         CisBmPatrolService {
	
	@Autowired
	private CisBmPatrolDao cisBmPatrolDao;
	
	@Autowired
	public CisBmPatrolServiceImpl(CisBmPatrolDao cisBmPatrolDao) {
		super(cisBmPatrolDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer patrolId){
		resetResultMap();
		try{
			CisBmPatrolInfo bean = cisBmPatrolDao.findOne(patrolId);
			bean.setDelSign("N");
			cisBmPatrolDao.save(bean);
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
	@Transactional
	public Map<String, Object> softDelList(List<Integer> patrolIds){
		resetResultMap();
		try{
			List<CisBmPatrolInfo> list = null;
			if(patrolIds.size()>0){
				List<CisBmPatrolInfo> listModel = cisBmPatrolDao.queryListById(patrolIds);
				for(CisBmPatrolInfo model : listModel){
					model.setDelSign("Y");
				}
				list = listModel;
				resultMap = super.doSave(list);
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}
	
}
