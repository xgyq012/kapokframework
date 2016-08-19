package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.cis.repository.CisBmSchoolDao;
import com.nateiot.cis.service.CisBmSchoolService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 学校信息
 * 
 * @author Administrator
 *
 */
@Service(value = "CisBmSchoolService")
@Transactional(readOnly = true)
public class CisBmSchoolServiceImpl extends
         BaseServiceImpl<CisBmSchoolDao, CisBmSchool, Integer> implements
         CisBmSchoolService {
	
	@Autowired
	private CisBmSchoolDao cisBmSchoolDao;
	
	@Autowired
	public CisBmSchoolServiceImpl(CisBmSchoolDao cisBmSchoolDao) {
		super(cisBmSchoolDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer unitsId){
		resetResultMap();
		try{
			CisBmSchool bean = cisBmSchoolDao.findOne(unitsId);
			bean.setDelSign("N");
			cisBmSchoolDao.save(bean);
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
				List<CisBmSchool> list = cisBmSchoolDao.queryListById(ids);
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
	
	/**
	 * 学校GIS查询
	 */
	@Override
	public Map<String, Object> querySchool(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		
		try {
			Page<Map<String, Object>> page = cisBmSchoolDao.querySchool(conditions, pageable);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询失败。");
		}
	
	return resultMap;
	}
	
}
