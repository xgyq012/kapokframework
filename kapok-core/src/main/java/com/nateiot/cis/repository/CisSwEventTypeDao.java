package com.nateiot.cis.repository;

import java.util.Map;

import com.nateiot.cis.domain.CisSwEventType;
import com.nateiot.core.repository.BaseDao;

/**
 * 服务办事 -- 事件类型管理
 * 
 *  @author Guohw
 */

public interface CisSwEventTypeDao extends BaseDao<CisSwEventType, Integer>,
		CisSwEventTypeDaoPlus{

}
