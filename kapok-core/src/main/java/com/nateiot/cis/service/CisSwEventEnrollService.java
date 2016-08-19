package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisSwEventEnroll;
import com.nateiot.cis.repository.CisSwEventEnrollDao;
import com.nateiot.core.service.BaseService;

/**
 * 服务办事 -- 事件登记
 * 
 *  @author Guohw
 */
public interface CisSwEventEnrollService extends 
		BaseService<CisSwEventEnrollDao, CisSwEventEnroll, Integer>{
	/**
	 * 查询 
	 */
	public Map<String, Object> enrollSelect(Integer enrollId);
	
	
	/**
	 * 保存 
	 */
	public Map<String, Object> enrollSave(CisSwEventEnroll cisSwEventEnroll, String root);
	
	/**
	 * 删除  
	 */
	public Map<String, Object> enrollDelete(Integer enrollId);
	
	/**
	 * 事件类别下拉框
	 */
	public Map<String, Object> comboBox(String eventType);
	
//	/**
//	 * 按钮权限判断
//	 */
//	public Map<String, Object> permission(Integer enrollId);
}
