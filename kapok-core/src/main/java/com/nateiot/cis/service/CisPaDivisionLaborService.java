package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisPaDivisionLabor;
import com.nateiot.cis.repository.CisPaDivisionLaborDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 党务建设 -- 两委分工
 * 
 *  @author Guohw
 */
public interface CisPaDivisionLaborService 
			extends BaseService<CisPaDivisionLaborDao, CisPaDivisionLabor, Integer>{
	
	/**
	 * 软删除 
	 */
	public Map<String, Object> softDel(Integer laborId);
	
	/**
	 * 保存 
	 */
	public Map<String, Object> saveEn(CisPaDivisionLabor entity);
	
	/**
	 * 查找党员 
	 */
	public Map<String, Object> findMember(Map<String, SearchFilter> conditions, Pageable pageable);

}
