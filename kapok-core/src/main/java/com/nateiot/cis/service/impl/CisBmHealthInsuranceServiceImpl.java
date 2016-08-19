package com.nateiot.cis.service.impl;

import com.nateiot.cis.domain.CisBmHealthInsurance;
import com.nateiot.cis.repository.CisBmHealthInsuranceDao;
import com.nateiot.cis.service.CisBmHealthInsuranceService;
import com.nateiot.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 医保信息
 * 
 * @author xiaguangjun
 *
 */
@Service(value = "CisBmHealthInsuranceService")
@Transactional(readOnly = true)
public class CisBmHealthInsuranceServiceImpl extends
         BaseServiceImpl<CisBmHealthInsuranceDao, CisBmHealthInsurance, Integer> implements
         CisBmHealthInsuranceService {
	
	@Autowired
	private CisBmHealthInsuranceDao cisBmHealthInsuranceDao;
	
	@Autowired
	public CisBmHealthInsuranceServiceImpl(CisBmHealthInsuranceDao cisBmHealthInsuranceDao) {
		super(cisBmHealthInsuranceDao);
	}
	
//	@Override
//	public Map<String, Object> softDel(Integer healthInsuranceId){
//		resetResultMap();
//		try{
//			CisBmHealthInsurance bean = cisBmHealthInsuranceDao.findOne(healthInsuranceId);
//			bean.setDelSign("N");
//			cisBmHealthInsuranceDao.save(bean);
//			resultMap.put(RESULT_CODE, 0);
//			resultMap.put(RESULT_MSG, "删除成功");
//		}catch(Exception e){
//			e.printStackTrace();
//			resultMap.put(RESULT_CODE, -1);
//			resultMap.put(RESULT_MSG, "删除出错");
//		}
//		
//		return resultMap;
//	}
	
//	@Override
//	@Transactional
//	public Map<String, Object> softDelList(List<Integer> healthInsuranceIds){
//		resetResultMap();
//		try{
//			List<CisBmHealthInsurance> list = null;
//			if(healthInsuranceIds.size()>0){
//				List<CisBmHealthInsurance> listModel = cisBmHealthInsuranceDao.queryListById(healthInsuranceIds);
//				for(CisBmHealthInsurance model : listModel){
//					model.setDelSign("Y");
//				}
//				list = listModel;
//				resultMap = super.doSave(list);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			resultMap.put(RESULT_CODE, -1);
//			resultMap.put(RESULT_MSG, "删除出错");
//		}
//		return resultMap;
//	}
	
}
