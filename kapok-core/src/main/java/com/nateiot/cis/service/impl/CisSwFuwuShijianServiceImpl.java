package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwFuwuShijian;
import com.nateiot.cis.domain.CisSwFwsjClmx;
import com.nateiot.cis.repository.CisSwFuwuShijianDao;
import com.nateiot.cis.repository.CisSwFwsjClmxDao;
import com.nateiot.cis.service.CisSwFuwuShijianService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 服务事件
 * @author xiewenhua
 *
 */
@Service("CisSwFuwuShijianService")
@Transactional
public class CisSwFuwuShijianServiceImpl extends 
    BaseServiceImpl<CisSwFuwuShijianDao, CisSwFuwuShijian, Integer> implements 
    CisSwFuwuShijianService {

	@Autowired
	private CisSwFuwuShijianDao cisSwFuwuShijianDao;
	
	@Autowired
	private CisSwFwsjClmxDao cisSwFwsjClmxDao;
	
	@Autowired
	public CisSwFuwuShijianServiceImpl(CisSwFuwuShijianDao d) {
		super(d);
	}

	@Override
	public Map<String, Object> getDetail(Integer fuwushijianId) {
		resetResultMap();
		try{
			CisSwFwsjClmx bean = cisSwFwsjClmxDao.findByFuwuShijianId(fuwushijianId);
			
			resultMap.put(RESULT_ROW, cisSwFwsjClmxDao.save(bean));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "加载数据成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "获取数据出错");
		}
		return resultMap;
	}


}
