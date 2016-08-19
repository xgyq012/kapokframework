package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisErProInvest;
import com.nateiot.cis.repository.CisErProInvestDao;
import com.nateiot.cis.service.CisErProInvestService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 经济运行 -- 项目招商
 * 
 *  @author guohuawen
 */
@Service(value = "cisErProInvestService")
@Transactional(readOnly = true)
public class CisErProInvestServiceImpl extends 
			BaseServiceImpl<CisErProInvestDao, CisErProInvest, Integer> implements
			CisErProInvestService{
	
	@Autowired
	private CisErProInvestDao cisErProInvestDao;
	
	@Autowired
	public CisErProInvestServiceImpl(CisErProInvestDao cisErProInvestDao) {
		super(cisErProInvestDao);
	}
	
	/**
	 * 软删除 
	 */
	@Override
	public Map<String, Object> softDel(Integer proInvestId){
		resetResultMap();
		try{
			CisErProInvest bean = cisErProInvestDao.findOne(proInvestId);
			bean.setDelSign("Y");
			
			resultMap.put(RESULT_ROW, cisErProInvestDao.save(bean));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除失败");
		}
		return null;
	}

}
