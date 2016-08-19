package com.nateiot.cis.repository;

import com.nateiot.cis.domain.CisSwFwsjClmx;
import com.nateiot.core.repository.BaseDao;

public interface CisSwFwsjClmxDao extends BaseDao<CisSwFwsjClmx, Integer>, CisSwFwsjClmxDaoPlus {

	public CisSwFwsjClmx findByFuwuShijianId(Integer fuwushijianId);
	
}
