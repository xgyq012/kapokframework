package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisSwFuwuShijian;
import com.nateiot.cis.repository.CisSwFuwuShijianDao;
import com.nateiot.core.service.BaseService;

public interface CisSwFuwuShijianService extends BaseService<CisSwFuwuShijianDao, CisSwFuwuShijian, Integer> {
	
	/**
	 * 事件督办 -- 获取详细 
	 */
	public Map<String, Object> getDetail(Integer fuwushijianId);

}
