package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmSafetyInfo;
import com.nateiot.cis.repository.CisBmSafetyDao;
import com.nateiot.cis.service.CisBmSafetyService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 技防信息
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmSafetyService")
@Transactional(readOnly = true)
public class CisBmSafetyServiceImpl extends
         BaseServiceImpl<CisBmSafetyDao, CisBmSafetyInfo, Integer> implements
         CisBmSafetyService {
	
	@Autowired
	private CisBmSafetyDao cisBmSafetyDao;
	
	@Autowired
	public CisBmSafetyServiceImpl(CisBmSafetyDao cisBmSafetyDao) {
		super(cisBmSafetyDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer safetyId){
		resetResultMap();
		try{
			CisBmSafetyInfo bean = cisBmSafetyDao.findOne(safetyId);
			bean.setDelSign("N");
			cisBmSafetyDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> safetyIds){
		resetResultMap();
		try{
			List<CisBmSafetyInfo> list = null;
			if(safetyIds.size()>0){
				List<CisBmSafetyInfo> listModel = cisBmSafetyDao.queryListById(safetyIds);
				for(CisBmSafetyInfo model : listModel){
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
