package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nateiot.cis.domain.CisEmHecha;
import com.nateiot.cis.repository.CisEmHechaDao;
import com.nateiot.cis.service.CisEmHechaService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisEmHechaService")
public class CisEmHechaServiceImpl extends BaseServiceImpl<CisEmHechaDao, CisEmHecha, Integer> implements CisEmHechaService{

	@Autowired
	private CisEmHechaDao cisEmHechaDao;
	
	@Autowired
	public CisEmHechaServiceImpl(CisEmHechaDao d) {
		super(d);
	}

	@Override
	public Map<String, Object> findByYingjiShijianId(Integer yingjiShijianId) {
		CisEmHecha entity = cisEmHechaDao.findByYingjiShijianId(yingjiShijianId).get(0);
		resetResultMap();
		try {
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROW, entity);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

}
