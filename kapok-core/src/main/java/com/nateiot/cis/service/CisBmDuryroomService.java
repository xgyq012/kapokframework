package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmDuryroomInfo;
import com.nateiot.cis.repository.CisBmDuryroomDao;
import com.nateiot.core.service.BaseService;

/**
 * 值班室信息
 * 
 * @author guohuawen
 *
 */
public interface CisBmDuryroomService extends
	BaseService<CisBmDuryroomDao, CisBmDuryroomInfo, Integer> {
	
	public Map<String, Object> softDel(Integer duryroomId);
	
	public Map<String, Object> softDelList(List<Integer> duryroomIds);
	
}
