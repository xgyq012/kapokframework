package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmHospital;
import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.cis.repository.CisBmHospitalDao;
import com.nateiot.cis.service.CisBmHospitalService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 医院信息
 * 
 * @author Administrator
 *
 */
@Service(value = "CisBmHospitalService")
@Transactional(readOnly = true)
public class CisBmHospitalServiceImpl extends
         BaseServiceImpl<CisBmHospitalDao, CisBmHospital, Integer> implements
         CisBmHospitalService {
	
	@Autowired
	private CisBmHospitalDao cisBmHospitalDao;
	
	@Autowired
	public CisBmHospitalServiceImpl(CisBmHospitalDao cisBmHospitalDao) {
		super(cisBmHospitalDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer hospitalId){
		resetResultMap();
		try{
			CisBmHospital bean = cisBmHospitalDao.findOne(hospitalId);
			bean.setDelSign("N");
			cisBmHospitalDao.save(bean);
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
	public Map<String, Object> delList(List<Integer> ids) {
	 
		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmHospital> list = cisBmHospitalDao.queryListById(ids);
				resultMap = super.doDelete(list);
				resultMap.put(RESULT_MSG, "删除成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		
		return resultMap;
		
	}
	
	
}
