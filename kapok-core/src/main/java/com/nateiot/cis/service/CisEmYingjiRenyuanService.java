package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisEmYingjiRenyuan;
import com.nateiot.cis.repository.CisEmYingjiRenyuanDao;
import com.nateiot.core.service.BaseService;

/**
 * 应急人员
 * @author xiewenhua
 *
 */
public interface CisEmYingjiRenyuanService extends BaseService<CisEmYingjiRenyuanDao, CisEmYingjiRenyuan, Integer> {
	public Map<String, Object> softDelList(List<Integer> ids);
}
