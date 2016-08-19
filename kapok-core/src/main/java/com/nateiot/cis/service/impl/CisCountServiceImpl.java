package com.nateiot.cis.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nateiot.cis.service.CisBmCommunityMeshService;
import com.nateiot.cis.service.CisBmFireControlService;
import com.nateiot.cis.service.CisBmHouseholderService;
import com.nateiot.cis.service.CisBmUnitsService;
import com.nateiot.cis.service.CisCountService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisCountService")
public class CisCountServiceImpl implements CisCountService{
	@Autowired
	private CisBmHouseholderService householderService;
	
	@Autowired
	private CisBmUnitsService unitsService;
	
	@Autowired
	private CisBmFireControlService controlService;
	
	@Autowired
	private CisBmCommunityMeshService meshService;

	@Override
	public Map<String, Object> getCountResult(String meshIds) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			//对人口方面的信息做统计，并将统计结果保存到结果集
			map.putAll(householderService.getCountResult(meshIds));

			map.putAll(unitsService.count(meshIds));
			
			map.putAll(controlService.count(meshIds));
			
			map.putAll(meshService.count(meshIds));
			
			resultMap.put(BaseServiceImpl.RESULT_ROW, map);
			resultMap.put(BaseServiceImpl.RESULT_CODE, 0);
			resultMap.put(BaseServiceImpl.RESULT_MSG, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(BaseServiceImpl.RESULT_CODE, -1);
			resultMap.put(BaseServiceImpl.RESULT_MSG, "查询出错"+ e.getMessage());
		}
		return resultMap;
	}
}
