package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmEntrepreneurStar;
import com.nateiot.cis.repository.CisBmEntrepreneurStarDao;
import com.nateiot.cis.service.CisBmEntrepreneurStarService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 创业之星
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmEntrepreneurStarService")
@Transactional(readOnly = true)
public class CisBmEntrepreneurStarServiceImpl extends
         BaseServiceImpl<CisBmEntrepreneurStarDao, CisBmEntrepreneurStar, Integer> implements
         CisBmEntrepreneurStarService {
	
	@Autowired
	private CisBmEntrepreneurStarDao cisBmEntrepreneurStarDao;
	
	@Autowired
	public CisBmEntrepreneurStarServiceImpl(CisBmEntrepreneurStarDao cisBmEntrepreneurStarDao) {
		super(cisBmEntrepreneurStarDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer entrepreneurStarId){
		resetResultMap();
		try{
			//CisBmEntrepreneurStar bean = cisBmEntrepreneurStarDao.findOne(entrepreneurStarId);
			//bean.setDelSign("N");
			//cisBmEntrepreneurStarDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> entrepreneurStarIds){
		resetResultMap();
		try{
			List<CisBmEntrepreneurStar> list = null;
			if(entrepreneurStarIds.size()>0){
				//List<CisBmEntrepreneurStar> listModel = cisBmEntrepreneurStarDao.queryListById(entrepreneurStarIds);
				//for(CisBmEntrepreneurStar model : listModel){
				//	model.setDelSign("Y");
				//}
				//list = listModel;
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
