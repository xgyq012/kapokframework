package com.nateiot.cis.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.common.SessionUtil;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.repository.GxwlSysUserDao;
import com.nateiot.cis.domain.CisErRisk;
import com.nateiot.cis.domain.CisErRiskStatus;
import com.nateiot.cis.repository.CisErRiskDao;
import com.nateiot.cis.repository.CisErRiskStatusDao;
import com.nateiot.cis.service.CisErRiskStatusService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 项目风险状态
 * 
 *  @author Guohw
 */
@Service(value = "cisErRiskStatusService")
@Transactional(readOnly = true)
public class CisErRiskStatusServiceImpl extends 
		BaseServiceImpl<CisErRiskStatusDao, CisErRiskStatus, Integer> 
		implements CisErRiskStatusService{
	
	@Autowired
	private CisErRiskStatusDao cisErRiskStatusDao;
	
	@Autowired
	private CisErRiskDao cisErRiskDao;
	
	@Autowired
	private GxwlSysUserDao gxwlSysUserDao;
	
	@Autowired
	public CisErRiskStatusServiceImpl(CisErRiskStatusDao cisErRiskStatusDao){
		super(cisErRiskStatusDao);
	}
	
	/**
	 * 判定状态 
	 */
	@Override
	public Map<String, Object> distinguish(Integer riskId){
		resetResultMap();
		try{
			CisErRisk bean = cisErRiskDao.findOne(riskId);
			String statusType = null;
			statusType = bean.getRiskStatus();
			
			resultMap.put(RESULT_ROW, statusType);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "获取数据成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "获取数据出错");
		}
		return resultMap;
	}
	
	/**
	 * 保存 
	 */
	@Override
	@Transactional
	public Map<String, Object> statusSave(CisErRiskStatus cisErRiskStatus) {
		resetResultMap();
		try{
			CisErRiskStatus entity = cisErRiskStatusDao.save(cisErRiskStatus);
			Integer statusId = cisErRiskStatus.getStatusId();
			
			if(statusId != null ){
				String realName = gxwlSysUserDao.findOne(SessionUtil.getCurrentUser().getUserId()).getRealname();
				
				Integer riskId = entity.getRiskId();
				CisErRisk bean = cisErRiskDao.findOne(riskId);
				if("draft".equals(bean.getRiskStatus())){
					bean.setRiskStatus("submit");
					cisErRiskStatus.setRiskStatus("submit");
					cisErRiskStatus.setDelSign("N");
					cisErRiskStatus.setOperator(realName);
					cisErRiskStatus.setStatusResult("Y");
				}else if("submit".equals(bean.getRiskStatus())){
					bean.setRiskStatus("assess");
					cisErRiskStatus.setRiskStatus("assess");
					cisErRiskStatus.setDelSign("N");
					cisErRiskStatus.setOperator(realName);
					cisErRiskStatus.setStatusResult("Y");
				}else if("assess".equals(bean.getRiskStatus())){
					bean.setRiskStatus("review");
					cisErRiskStatus.setRiskStatus("review");
					cisErRiskStatus.setDelSign("N");
					cisErRiskStatus.setOperator(realName);
					cisErRiskStatus.setStatusResult("Y");
				}else if("review".equals(bean.getRiskStatus())){
					bean.setRiskStatus("audit");
					cisErRiskStatus.setRiskStatus("audit");
					cisErRiskStatus.setDelSign("N");
					cisErRiskStatus.setOperator(realName);
					cisErRiskStatus.setStatusResult("Y");
				}
				
				resultMap.put(RESULT_ROW, entity);
				resultMap.put(RESULT_CODE, 0);
				resultMap.put(RESULT_MSG, "获取数据成功");
				return resultMap;
			}else{
				
				resultMap.put(RESULT_ROW, cisErRiskStatusDao.save(cisErRiskStatus));
				resultMap.put(RESULT_CODE, 0);
				resultMap.put(RESULT_MSG, "获取数据成功");
				return resultMap;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "获取数据出错");
		}
		return resultMap;
	}

	/**
	 * 加载详细 
	 */
	@Override
	public Map<String, Object> statusSelect(Integer riskId){
		resetResultMap();
		try{
			CisErRisk bean = cisErRiskDao.findByRiskId(riskId);
			
			resultMap.put(RESULT_ROW, bean);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "数据加载成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "数据加载出错");
		}
		return resultMap;
	}
	
	/**
	 * 提交不通过 
	 */
	@Override
	@Transactional
	public Map<String, Object> noPass(CisErRiskStatus cisErRiskStatus){
		resetResultMap();
		try{
			CisErRiskStatus bean = cisErRiskStatusDao.save(cisErRiskStatus);
			Integer stautsId = bean.getStatusId();
			Integer riskId = bean.getRiskId();
			
			CisErRisk risk = cisErRiskDao.findOne(riskId);
			String riskStatus =  risk.getRiskStatus();
			
			String realName = gxwlSysUserDao.findOne(SessionUtil.getCurrentUser().getUserId()).getRealname();
			cisErRiskStatus.setStatusResult("N");
			cisErRiskStatus.setOperator(realName);
			
			if("submit".equals(riskStatus)){
				cisErRiskStatus.setRiskStatus("draft");
				bean.setRiskStatus("assess");
			}else if("assess".equals(riskStatus)){
				cisErRiskStatus.setRiskStatus("review");
			}else if("review".equals(riskStatus)){
				cisErRiskStatus.setRiskStatus("audit");
			}
			
			resultMap.put(RESULT_ROW, cisErRiskStatusDao.save(cisErRiskStatus));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错");
		}
		return resultMap;
	}
}
