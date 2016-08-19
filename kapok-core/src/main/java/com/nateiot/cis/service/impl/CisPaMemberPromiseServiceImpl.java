package com.nateiot.cis.service.impl;

import java.util.Map;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.cis.domain.CisPaMemberPromise;
import com.nateiot.cis.repository.CisPaMemberPromiseDao;
import com.nateiot.cis.service.CisPaMemberPromiseService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 党务建设 -- 党员公开承诺活动登记表
 * 
 * @author Guohw
 */
@Service(value = "CisPaMemberPromiseService")
@Transactional(readOnly=true)
public class CisPaMemberPromiseServiceImpl 
		extends BaseServiceImpl<CisPaMemberPromiseDao, CisPaMemberPromise, Integer>
		implements CisPaMemberPromiseService{
	
	@Autowired
	private CisPaMemberPromiseDao cisPaMemberPromiseDao;
	
	@Autowired
	private CisPaMemberPromiseServiceImpl(CisPaMemberPromiseDao d){
		super(d);
	}

	/**
	 * 软删除 
	 */
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer promiseId) {
		resetResultMap();
		try{
			CisPaMemberPromise entity = cisPaMemberPromiseDao.findOne(promiseId);
			entity.setDelSign("Y");
			
			resultMap.put(RESULT_ROW, cisPaMemberPromiseDao.save(entity));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}

	/**
	 * 查找党员 
	 */
	@Override
	public Map<String, Object> findMember(Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisPaMemberPromiseDao.findMember(conditions, pageable);
			
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		return resultMap;
	}
	
	/**
	 * 查询  
	 */
	@Override
	public Map<String, Object> searchBy(String timeGte, String timeLte,
			Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisPaMemberPromiseDao.searchBy(timeGte, timeLte, conditions, pageable);
			
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		return resultMap;
	}

}
