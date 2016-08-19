package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisSwEnrollRowTable;
import com.nateiot.cis.repository.CisSwEnrollRowTableDao;
import com.nateiot.core.service.BaseService;

/**
 *  服务办事 -- 事件登记行表
 *  
 *  @author Guohw
 */
public interface CisSwEnrollRowTableService extends 
		BaseService<CisSwEnrollRowTableDao, CisSwEnrollRowTable, Integer>{
	
	/**
	 * 保存 
	 */
	public Map<String, Object> rowTableSave(CisSwEnrollRowTable cisSwEnrollRowTable);
	
	/**
	 * 评价
	 */
	public Map<String, Object> estimate(Integer enrollId, String remark, Integer backLogId, String enrollStatus);
}
