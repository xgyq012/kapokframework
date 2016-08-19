package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisEmYjsjlx;
import com.nateiot.cis.repository.CisEmYjsjlxDao;
import com.nateiot.core.service.BaseService;

/**
 * 应急事件类型
 * @author xiewenhua
 *
 */
public interface CisEmYjsjlxService extends BaseService<CisEmYjsjlxDao, CisEmYjsjlx, Integer>{

	public Map<String, Object> softDelList(List<Integer> list);
	
}
