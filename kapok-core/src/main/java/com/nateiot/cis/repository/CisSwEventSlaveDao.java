package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisSwEventSlave;
import com.nateiot.core.repository.BaseDao;


/**
 * 服务办事 -- 事件类型行表
 * 
 *  @author Guohw
 */
public interface CisSwEventSlaveDao extends BaseDao<CisSwEventSlave, Integer>,
		CisSwEventSlaveDaoPlus{

//	public List<CisSwEventSlave> findByTypeId(Integer id);

}
