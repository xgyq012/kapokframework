package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisPaPostDuty;
import com.nateiot.cis.repository.CisPaPostDutyDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 党务建设 -- 党员设岗定责登记表
 * 
 * @author Guohw
 */
public interface CisPaPostDutyService extends BaseService<CisPaPostDutyDao, CisPaPostDuty, Integer>{
	
	/**
	 * 软删除 
	 */
	public Map<String, Object> softDel(Integer dutyId);
	
	/**
	 * 查找党员 
	 */
	public Map<String, Object> findMember(Map<String, SearchFilter> conditions, Pageable pageable);
	
	/**
	 * 查询
	 * 
	 *  @param conditions, pageable
	 *  @return
	 */
	public Map<String, Object> searchBy(Map<String, SearchFilter> conditions, Pageable pageable);

}
