package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nateiot.cis.domain.CisBmHospitalDisputes;
import com.nateiot.cis.repository.CisBmHospitalDisputesDao;
import com.nateiot.cis.service.CisBmHospitalDisputesService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 医院纠纷
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmHospitalDisputesService")
@Transactional(readOnly = true)
public class CisBmHospitalDisputesServiceImpl extends
         BaseServiceImpl<CisBmHospitalDisputesDao, CisBmHospitalDisputes, Integer> implements
         CisBmHospitalDisputesService {
	
	@Autowired
	private CisBmHospitalDisputesDao cisBmHospitalDisputesDao;
	
	@Autowired
	public CisBmHospitalDisputesServiceImpl(CisBmHospitalDisputesDao cisBmHospitalDisputesDao) {
		super(cisBmHospitalDisputesDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer hoDisputesId){
		resetResultMap();
		try{
			CisBmHospitalDisputes bean = cisBmHospitalDisputesDao.findOne(hoDisputesId);
			bean.setDelSign("N");
			cisBmHospitalDisputesDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> hoDisputesIds){
		resetResultMap();
		try{
			List<CisBmHospitalDisputes> list = null;
			if(hoDisputesIds.size()>0){
				List<CisBmHospitalDisputes> listModel = cisBmHospitalDisputesDao.queryListById(hoDisputesIds);
				for(CisBmHospitalDisputes model : listModel){
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
