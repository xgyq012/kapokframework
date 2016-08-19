package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisErProBuild;
import com.nateiot.cis.repository.CisErProBuildDao;
import com.nateiot.cis.service.CisErProBuildService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 经济运行 -- 项目建设
 * 
 * @author guohuawen
 */
@Service(value = "cisErProBuildService")
@Transactional(readOnly = true)
public class CisErProBuildServiceImpl extends
		BaseServiceImpl<CisErProBuildDao, CisErProBuild, Integer> implements
		CisErProBuildService{
	
	@Autowired
	private CisErProBuildDao cisErProBuildDao;
	
	@Autowired
	public CisErProBuildServiceImpl(CisErProBuildDao cisErProBuildDao) {
		super(cisErProBuildDao);
	}

	@Override
	public Map<String, Object> softDel(Integer proBuildId) {
		resetResultMap();
		try{
			CisErProBuild bean = cisErProBuildDao.findOne(proBuildId);
			bean.setDelSign("Y");
			resultMap.put(RESULT_ROW, cisErProBuildDao.save(bean));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "数据删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除失败");
		}
		return resultMap;
	}
	
}
