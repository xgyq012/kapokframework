package com.nateiot.cis.repository;

import com.nateiot.cis.domain.CisSwEventEnroll;
import com.nateiot.core.repository.BaseDao;

/**
 * 服务办事 -- 事件登记
 * 
 *  @author Guohw
 */
public interface CisSwEventEnrollDao extends BaseDao<CisSwEventEnroll, Integer>,
			CisSwEventEnrollDaoPlus{

	CisSwEventEnroll findByEnrollId(Integer enrollId);
	
	

}
