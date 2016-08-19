package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmFoodSafety;
import com.nateiot.cis.repository.CisBmFoodSafetyDao;
import com.nateiot.cis.service.CisBmFoodSafetyService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 食品安全
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmFoodSafetyService")
@Transactional(readOnly = true)
public class CisBmFoodSafetyServiceImpl extends
         BaseServiceImpl<CisBmFoodSafetyDao, CisBmFoodSafety, Integer> implements
         CisBmFoodSafetyService {
	
	@Autowired
	private CisBmFoodSafetyDao cisBmFoodSafetyDao;
	
	@Autowired
	public CisBmFoodSafetyServiceImpl(CisBmFoodSafetyDao cisBmFoodSafetyDao) {
		super(cisBmFoodSafetyDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer saFoodsId){
		resetResultMap();
		try{
			CisBmFoodSafety bean = cisBmFoodSafetyDao.findOne(saFoodsId);
			bean.setDelSign("N");
			cisBmFoodSafetyDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> saFoodsIds){
		resetResultMap();
		try{
			List<CisBmFoodSafety> list = null;
			if(saFoodsIds.size()>0){
				List<CisBmFoodSafety> listModel = cisBmFoodSafetyDao.queryListById(saFoodsIds);
				for(CisBmFoodSafety model : listModel){
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
