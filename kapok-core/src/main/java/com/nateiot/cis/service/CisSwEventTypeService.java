package com.nateiot.cis.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

import com.nateiot.cis.domain.CisSwEventType;
import com.nateiot.cis.repository.CisSwEventTypeDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 服务办事 -- 事件类型管理
 * 
 *  @author Guohw
 */
public interface CisSwEventTypeService extends BaseService<
		CisSwEventTypeDao, CisSwEventType, Integer>{
	
	public Map<String, Object> eventSave(CisSwEventType cisSwEventType, String root);
	
	public Map<String, Object> getData(Map<String, SearchFilter> conditions, Pageable pageable);
	
}
