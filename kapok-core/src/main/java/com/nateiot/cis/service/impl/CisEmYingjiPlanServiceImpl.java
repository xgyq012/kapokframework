package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEmPlanMx;
import com.nateiot.cis.domain.CisEmPlanRenyuan;
import com.nateiot.cis.domain.CisEmYingjiPlan;
import com.nateiot.cis.repository.CisEmPlanRenyuanDao;
import com.nateiot.cis.repository.CisEmYingjiPlanDao;
import com.nateiot.cis.service.CisEmPlanMxService;
import com.nateiot.cis.service.CisEmYingjiPlanService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 应急预案
 * @author xiewenhua
 *
 */
@Service(value = "CisEmYingjiPlanService")
@Transactional
public class CisEmYingjiPlanServiceImpl extends 
   BaseServiceImpl<CisEmYingjiPlanDao, CisEmYingjiPlan, Integer> implements CisEmYingjiPlanService {

	@Autowired
	private CisEmYingjiPlanDao cisEmYingjiPlanDao;
	
	@Autowired
	private CisEmPlanRenyuanDao cisEmPlanRenyuanDao;
	
	@Autowired
	private CisEmPlanMxService cisEmPlanMxService;
	
	@Autowired
	public CisEmYingjiPlanServiceImpl(CisEmYingjiPlanDao d) {
		super(d);
	}
	
	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer id) {
		CisEmYingjiPlan plan = cisEmYingjiPlanDao.findOne(id);
		plan.setDelSign("Y");
/*		if(! plan.getPlanRenyuanList().isEmpty()){
			List<CisEmPlanRenyuan> delRenyuanList = plan.getPlanRenyuanList();
			cisEmPlanRenyuanDao.deleteInBatch(delRenyuanList);	
		}*/
		
		return super.doSave(plan);
	}

	@Override
	@Transactional
	public Map<String, Object> doDelete(List<Integer> ids) {
		try {
		   resetResultMap();
		   for(Integer id : ids){
			   this.doDelete(id);
		   }
		   setResultStatus(0, "删除成功");
		   return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}
	
	
}
