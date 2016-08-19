package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisPaPostDuty;
import com.nateiot.cis.repository.CisPaPostDutyDao;
import com.nateiot.cis.service.CisPaPostDutyService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 党务建设 -- 党员设岗定责登记表
 * 
 * @author Guohw
 */
@Service(value = "CisPaPostDutyService")
@Transactional(readOnly=true)
public class CisPaPostDutyServiceImpl 
		extends BaseServiceImpl<CisPaPostDutyDao, CisPaPostDuty, Integer>
		implements CisPaPostDutyService{
	
	@Autowired
	private CisPaPostDutyDao cisPaPostDutyDao;
	
	@Autowired
	public CisPaPostDutyServiceImpl(CisPaPostDutyDao d){
		super(d);
	}

	/**
	 * 软删除 
	 */
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer dutyId) {
		resetResultMap();
		try{
			CisPaPostDuty entity = cisPaPostDutyDao.findOne(dutyId);
			entity.setDelSign("Y");
			
			resultMap.put(RESULT_ROW, cisPaPostDutyDao.save(entity));
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
	public Map<String, Object> findMember(Map<String, SearchFilter> conditions,
			Pageable pageable) {
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisPaPostDutyDao.findMember(conditions, pageable); 
			
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
	public Map<String, Object> searchBy(Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisPaPostDutyDao.searchBy(conditions, pageable);
			
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
