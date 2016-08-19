package com.nateiot.cis.repository;

import com.nateiot.cis.domain.CisSwFuwuShijian;
import com.nateiot.core.repository.BaseDao;

public interface CisSwFuwuShijianDao extends BaseDao<CisSwFuwuShijian, Integer>, CisSwFuwuShijianDaoPlus {
	
	public CisSwFuwuShijian findByFuwuShijianId(Integer fuwuShijianId);

}
