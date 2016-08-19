package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisPaTrainMembers;
import com.nateiot.cis.repository.CisPaTrainMembersDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 党务建设 -- 党员培训情况登记表
 * 
 * @author Guohw
 */
public interface CisPaTrainMembersService 
		extends BaseService<CisPaTrainMembersDao, CisPaTrainMembers, Integer>{
	
	/**
	 * 软删除 
	 */
	public Map<String, Object> softDel(Integer trainId);
	
	/**
	 * 查询
	 * 
	 *  @param conditions, pageable
	 *  @return
	 */
	public Map<String, Object> searchBy(String timeGte, String timeLte, Map<String, SearchFilter> conditions, Pageable pageable);

}
