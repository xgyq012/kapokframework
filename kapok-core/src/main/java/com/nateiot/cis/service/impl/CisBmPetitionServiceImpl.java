package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmPetitionInfo;
import com.nateiot.cis.repository.CisBmPetitionDao;
import com.nateiot.cis.service.CisBmPetitionService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 信访信息
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmPetitionService")
@Transactional(readOnly = true)
public class CisBmPetitionServiceImpl extends
         BaseServiceImpl<CisBmPetitionDao, CisBmPetitionInfo, Integer> implements
         CisBmPetitionService {
	
	@Autowired
	private CisBmPetitionDao cisBmPetitionDao;
	
	@Autowired
	public CisBmPetitionServiceImpl(CisBmPetitionDao cisBmPetitionDao) {
		super(cisBmPetitionDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer petitionId){
		resetResultMap();
		try{
			CisBmPetitionInfo bean = cisBmPetitionDao.findOne(petitionId);
			bean.setDelSign("N");
			cisBmPetitionDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> petitionIds){
		resetResultMap();
		try{
			List<CisBmPetitionInfo> list = null;
			if(petitionIds.size()>0){
				List<CisBmPetitionInfo> listModel = cisBmPetitionDao.queryListById(petitionIds);
				for(CisBmPetitionInfo model : listModel){
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
