package com.nateiot.cis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEsOversee;
import com.nateiot.cis.domain.CisSwEventEnroll;
import com.nateiot.cis.domain.CisSwFuwuShijian;
import com.nateiot.cis.domain.CisSwFwsjClmx;
import com.nateiot.cis.repository.CisEsOverseeDao;
import com.nateiot.cis.repository.CisSwEnrollRowTableDao;
import com.nateiot.cis.repository.CisSwEventEnrollDao;
import com.nateiot.cis.repository.CisSwFuwuShijianDao;
import com.nateiot.cis.repository.CisSwFwsjClmxDao;
import com.nateiot.cis.service.CisEsOverseeService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 考核督办 -- 事件督办
 * 
 *  @author guohuawen
 */
@Service(value = "cisEsOverseeService")
@Transactional(readOnly = true)
public class CisEsOverseeServiceImpl extends BaseServiceImpl<CisEsOverseeDao, CisEsOversee, Integer>
		implements CisEsOverseeService{
	
	@Autowired
	private CisSwEventEnrollDao cisSwEventEnrollDao;

	@Autowired
	private CisSwEnrollRowTableDao cisSwEnrollRowTableDao;
	
	@Autowired
	private CisEsOverseeDao cisEsOverseeDao;
	
	@Autowired
	public CisEsOverseeServiceImpl(CisEsOverseeDao cisEsOverseeDao){
		super(cisEsOverseeDao);
	}

	/**
	 * 查询 
	 */
	@Override
	public Map<String, Object> enrollSearch(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisEsOverseeDao.search(conditions, pageable);
			
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
	 * 详细 
	 */
	@Override
	public Map<String, Object> enrollSelect(Integer enrollId) {
		resetResultMap();
		try{
			CisSwEventEnroll bean = cisSwEventEnrollDao.findOne(enrollId);
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("enroll", cisSwEventEnrollDao.save(bean));
			row.put("rowTable", cisSwEnrollRowTableDao.findByEnrollId(enrollId));
			resultMap.put(RESULT_ROW, row);
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
