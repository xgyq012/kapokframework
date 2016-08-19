package com.nateiot.cis.service.impl;

import java.util.Map;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisPaTrainMembers;
import com.nateiot.cis.repository.CisPaTrainMembersDao;
import com.nateiot.cis.service.CisPaTrainMembersService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 党务建设 -- 党员培训情况登记表
 * 
 * @author Guohw
 */
@Service(value = "CisPaTrainMembersService")
@Transactional(readOnly=true)
public class CisPaTrainMembersServiceImpl extends BaseServiceImpl<CisPaTrainMembersDao, CisPaTrainMembers, Integer>
		implements CisPaTrainMembersService{
	

	@Autowired
	private CisPaTrainMembersDao cisPaTrainMembersDao;
	
	@Autowired
	public CisPaTrainMembersServiceImpl(CisPaTrainMembersDao d){
		super(d);
	}

	/**
	 * 软删除 
	 */
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer trainId) {
		resetResultMap();
		try{
			CisPaTrainMembers entity = cisPaTrainMembersDao.findOne(trainId);
			entity.setDelSign("Y");
			
			resultMap.put(RESULT_ROW, cisPaTrainMembersDao.save(entity));
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
			Page<Map<String, Object>> page = cisPaTrainMembersDao.searchBy(timeGte, timeLte, conditions, pageable);
			
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
