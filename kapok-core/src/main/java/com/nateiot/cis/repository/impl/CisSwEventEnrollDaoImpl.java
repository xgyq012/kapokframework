package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisSwEventType;
import com.nateiot.cis.repository.CisSwEventEnrollDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;


/**
 * 服务办事 -- 事件登记
 * 
 *  @author Guohw
 */
public class CisSwEventEnrollDaoImpl extends BaseDaoImpl 
		implements CisSwEventEnrollDaoPlus{

	/**
	 * 事件类型下拉框 
	 */
	@Override
	public List<Map<String, Object>> findTheTypeName(String eventType) {
		String sqlString = "  SELECT"
				+"      ty.TYPE_ID as typeId,"
				+"      ty.TYPE_NAME as typeName,"
				+"      ty.EVENT_TYPE as eventType,"
				+"      ty.CREATE_BY as createBy"
				+"  FROM"
				+"      cis_sw_event_type ty"
				+"  WHERE"
				+"      ty.EVENT_TYPE = '"+ eventType +"'";
		
		return selectBySql(sqlString);
	}
	
}
