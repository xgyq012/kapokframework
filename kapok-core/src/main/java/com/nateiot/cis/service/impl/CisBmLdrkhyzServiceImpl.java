package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmBirthCertificate;
import com.nateiot.cis.domain.CisBmLdrkhyz;
import com.nateiot.cis.repository.CisBmLdrkhyzDao;
import com.nateiot.cis.service.CisBmLdrkhyzService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisBmLdrkhyzService")
@Transactional
public class CisBmLdrkhyzServiceImpl  extends
			BaseServiceImpl<CisBmLdrkhyzDao, CisBmLdrkhyz, Integer> implements CisBmLdrkhyzService{

	@Autowired
	public CisBmLdrkhyzServiceImpl(CisBmLdrkhyzDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmLdrkhyzDao cisBmLdrkhyzDao;

	@Override
	@Transactional
	public Map<String, Object> delList(List<Integer> ids) {

		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmLdrkhyz> list = cisBmLdrkhyzDao.queryListById(ids);
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
