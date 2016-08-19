package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisPaCommunityDuty;
import com.nateiot.cis.repository.CisPaCommunityDutyDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 党务建设 -- 社区党务公开表
 * 
 * @author Guohw
 */
public interface CisPaCommunityDutyService extends 
		BaseService<CisPaCommunityDutyDao, CisPaCommunityDuty, Integer>{
	
	/**
	 * 软删除 
	 */
	public Map<String, Object> softDel(Integer meetingId);
	
	/**
	 * 查询
	 * 
	 *  @param conditions, pageable
	 *  @return
	 */
	public Map<String, Object> searchBy(String timeGte, String timeLte, Map<String, SearchFilter> conditions, Pageable pageable);

}
