package com.nateiot.cis.service;

import com.nateiot.cis.domain.CisEmYingjiShijian;
import com.nateiot.cis.repository.CisEmYingjiShijianDao;
import com.nateiot.core.service.BaseService;

public interface CisEmYingjiShijianService extends BaseService<CisEmYingjiShijianDao, CisEmYingjiShijian, Integer> {
	/**
	 * 当前用户是给点应急事件的核查人或审查人
	 * @param yingjiShijianId
	 * @return
	 */
	public boolean isHechaOrShenchaRen(Integer yingjiShijianId);

}
