package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisSwEnrollRowTable;
import com.nateiot.core.repository.BaseDao;

/**
 *  服务办事 -- 事件登记行表
 *  
 *  @author Guohw
 */
public interface CisSwEnrollRowTableDao extends BaseDao<CisSwEnrollRowTable, Integer>,
				CisSwEnrollRowTableDaoPlus{

	public List<CisSwEnrollRowTable> findByEnrollId(Integer enrollId);

}
