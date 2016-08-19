package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCampusSafety;
import com.nateiot.cis.repository.CisBmCampusSafetyDao;
import com.nateiot.cis.service.CisBmCampusSafetyService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 公共安全--校园安全
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmCampusSafetyService")
@Transactional(readOnly = true)
public class CisBmCampusSafetyServiceImpl extends
         BaseServiceImpl<CisBmCampusSafetyDao, CisBmCampusSafety, Integer> implements
         CisBmCampusSafetyService {
	
	@Autowired
	private CisBmCampusSafetyDao cisBmCampusSafetyDao;
	
	@Autowired
	public CisBmCampusSafetyServiceImpl(CisBmCampusSafetyDao cisBmCampusSafetyDao) {
		super(cisBmCampusSafetyDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer saCampusId){
		resetResultMap();
		try{
			CisBmCampusSafety bean = cisBmCampusSafetyDao.findOne(saCampusId);
			bean.setDelSign("N");
			cisBmCampusSafetyDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> saCampusIds){
		resetResultMap();
		try{
			List<CisBmCampusSafety> list = null;
			if(saCampusIds.size()>0){
				List<CisBmCampusSafety> listModel = cisBmCampusSafetyDao.queryListById(saCampusIds);
				for(CisBmCampusSafety model : listModel){
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
