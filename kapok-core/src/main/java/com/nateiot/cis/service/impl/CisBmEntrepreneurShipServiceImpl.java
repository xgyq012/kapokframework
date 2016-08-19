package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCultivate;
import com.nateiot.cis.domain.CisBmEntrepreneurShip;
import com.nateiot.cis.repository.CisBmEntrepreneurShipDao;
import com.nateiot.cis.service.CisBmEntrepreneurShipService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 创业商户
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmEntrepreneurShipService")
@Transactional(readOnly = true)
public class CisBmEntrepreneurShipServiceImpl extends
         BaseServiceImpl<CisBmEntrepreneurShipDao, CisBmEntrepreneurShip, Integer> implements
         CisBmEntrepreneurShipService {
	
	@Autowired
	private CisBmEntrepreneurShipDao cisBmEntrepreneurShipDao;
	
	@Autowired
	public CisBmEntrepreneurShipServiceImpl(CisBmEntrepreneurShipDao cisBmEntrepreneurShipDao) {
		super(cisBmEntrepreneurShipDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer entrepreneurShipId){
		resetResultMap();
		try{
			//CisBmEntrepreneurShip bean = cisBmEntrepreneurShipDao.findOne(entrepreneurShipId);
			//bean.setDelSign("N");
			//cisBmEntrepreneurShipDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> entrepreneurShipIds){
		resetResultMap();
		try{
			List<CisBmEntrepreneurShip> list = null;
			if(entrepreneurShipIds.size()>0){
				//List<CisBmEntrepreneurShip> listModel = cisBmEntrepreneurShipDao.queryListById(entrepreneurShipIds);
				//for(CisBmEntrepreneurShip model : listModel){
					//model.setDelSign("Y");
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
