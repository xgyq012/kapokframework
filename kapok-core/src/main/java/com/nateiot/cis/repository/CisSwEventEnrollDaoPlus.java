package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisSwEventType;


/**
 * 服务办事 -- 事件登记
 * 
 *  @author Guohw
 */
public interface CisSwEventEnrollDaoPlus {
	
	/**
	 * 事件类型下拉框 
	 */
	public List<Map<String, Object>> findTheTypeName(String eventType);

}
