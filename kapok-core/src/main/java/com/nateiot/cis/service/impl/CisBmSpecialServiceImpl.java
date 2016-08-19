package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.cis.domain.CisBmSpecial;
import com.nateiot.cis.repository.CisBmSpecialDao;
import com.nateiot.cis.service.CisBmSpecialService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 特殊行业
 * 
 * @author Administrator
 *
 */
@Service(value = "CisBmSpecialService")
@Transactional(readOnly = true)
public class CisBmSpecialServiceImpl extends
         BaseServiceImpl<CisBmSpecialDao, CisBmSpecial, Integer> implements
         CisBmSpecialService {
	
	@Autowired
	private CisBmSpecialDao cisBmSpecialDao;
	
	@Autowired
	public CisBmSpecialServiceImpl(CisBmSpecialDao cisBmSpecialDao) {
		super(cisBmSpecialDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer specialId){
		resetResultMap();
		try{
			CisBmSpecial bean = cisBmSpecialDao.findOne(specialId);
			bean.setDelSign("N");
			cisBmSpecialDao.save(bean);
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
				List<CisBmSpecial> list = cisBmSpecialDao.queryListById(ids);
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
