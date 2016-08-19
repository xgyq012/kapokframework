package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisPaCommunityDuty;
import com.nateiot.cis.repository.CisPaCommunityDutyDao;
import com.nateiot.cis.service.CisPaCommunityDutyService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 党务建设 -- 社区党务公开表
 * 
 * @author Guohw
 */
@Service(value = "cisPaCommunityDutyService")
@Transactional(readOnly=true)
public class CisPaCommunityDutyServiceImpl extends 
		BaseServiceImpl<CisPaCommunityDutyDao, CisPaCommunityDuty, Integer>
		implements CisPaCommunityDutyService{
	
	@Autowired
	private CisPaCommunityDutyDao cisPaCommunityDutyDao;
	
	@Autowired
	public CisPaCommunityDutyServiceImpl(CisPaCommunityDutyDao d) {
		super(d);
	}

	/**
	 * 软删除 
	 */
	@Override
	@Transient
	public Map<String, Object> softDel(Integer meetingId) {
		resetResultMap();
		try{
			CisPaCommunityDuty entity = cisPaCommunityDutyDao.findOne(meetingId);
			entity.setDelSign("Y");
			
			resultMap.put(RESULT_ROW, cisPaCommunityDutyDao.save(entity));
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
	 * 查询  
	 */
	@Override
	public Map<String, Object> searchBy(String timeGte, String timeLte,
			Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisPaCommunityDutyDao.searchBy(timeGte, timeLte, conditions, pageable);
			
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
