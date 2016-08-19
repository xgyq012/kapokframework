package com.nateiot.cis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.common.SessionUtil;
import com.nateiot.base.repository.GxwlSysUserDao;
import com.nateiot.cis.domain.CisErRisk;
import com.nateiot.cis.domain.CisErRiskStatus;
import com.nateiot.cis.repository.CisErRiskDao;
import com.nateiot.cis.repository.CisErRiskStatusDao;
import com.nateiot.cis.service.CisErRiskService;
import com.nateiot.core.service.impl.BaseServiceImpl;


/**
 * 风险项目评估
 * 
 *  @author Guohw
 */
@Service(value = "cisErRiskService")
@Transactional(readOnly = true)
public class CisErRiskServiceImpl extends 
		BaseServiceImpl<CisErRiskDao, CisErRisk, Integer> 
		implements CisErRiskService{
	
	@Autowired
	private CisErRiskDao cisErRiskDao;
	
	@Autowired
	private CisErRiskStatusDao cisErRiskStatusDao;
	
	@Autowired
	private GxwlSysUserDao gxwlSysUserDao;
	
	@Autowired
	public CisErRiskServiceImpl(CisErRiskDao cisErRiskDao) {
		super(cisErRiskDao);
	}
	
	/**
	 * 软删除 
	 */
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer riskId){
		resetResultMap();
		try{
			CisErRisk bean = cisErRiskDao.findOne(riskId);
			bean.setDelSign("Y");
			
			resultMap.put(RESULT_ROW, cisErRiskDao.save(bean));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}
	
	/**
	 * 详细(回复过滤--删除标记、当前状态)
	 */
//	@Override
//	public Map<String, Object> riskSelect(Integer riskId) {
//		resetResultMap();
//		try{
//			CisErRisk risk = cisErRiskDao.findOne(riskId);
//			
//			Map<String, Object> row = new HashMap<String, Object>();
//			List<CisErRiskStatus> list = new ArrayList<CisErRiskStatus>();
//			List<CisErRiskStatus> list2 = new ArrayList<CisErRiskStatus>();
//			list2 = cisErRiskStatusDao.findByRiskId(riskId);
//			for(CisErRiskStatus status : list2){
//				if(!"draft".equals(status.getRiskStatus())){
//					if("submit".equals(risk.getRiskStatus())){
//						if("submit".equals(status.getRiskStatus())&&"N".equals(status.getDelSign())){
//								list.add(status);
//						}
//					}else if("assess".equals(risk.getRiskStatus())){
//						if("submit".equals(status.getRiskStatus())||
//								"assess".equals(status.getRiskStatus())){
//							if("N".equals(status.getDelSign())){
//								list.add(status);
//							}
//						}
//					}else if("review".equals(risk.getRiskStatus())){
//						if("submit".equals(status.getRiskStatus())||
//								"assess".equals(status.getRiskStatus())||
//								"review".equals(status.getRiskStatus())){
//							if("N".equals(status.getDelSign())){
//								list.add(status);
//							}
//						}
//					}else if("audit".equals(risk.getRiskStatus())){
//						if("submit".equals(status.getRiskStatus())||
//								"assess".equals(status.getRiskStatus())||
//								"review".equals(status.getRiskStatus())||
//								"audit".equals(status.getRiskStatus())){
//							if("N".equals(status.getDelSign())){
//								list.add(status);
//							}
//						}
//					}
//				}
//			}
//			
//			row.put("risk", cisErRiskDao.save(risk));
//			row.put("riskStatus", list);
//			resultMap.put(RESULT_ROW, row);
//			resultMap.put(RESULT_CODE, 0);
//			resultMap.put(RESULT_MSG, "获取数据成功");
//		}catch(Exception e){
//			e.printStackTrace();
//			resultMap.put(RESULT_CODE, -1);
//			resultMap.put(RESULT_MSG, "获取数据出错");
//		}
//		return resultMap;
//	}
//	
	/**
	 * 详细(回复过滤--当前状态)
	 */
//	@Override
//	public Map<String, Object> riskSelect(Integer riskId) {
//		resetResultMap();
//		try{
//			CisErRisk risk = cisErRiskDao.findOne(riskId);
//			
//			Map<String, Object> row = new HashMap<String, Object>();
//			List<CisErRiskStatus> list = new ArrayList<CisErRiskStatus>();
//			List<CisErRiskStatus> list2 = new ArrayList<CisErRiskStatus>();
//			list2 = cisErRiskStatusDao.findByRiskId(riskId);
//			for(CisErRiskStatus status : list2){
//				if(!"draft".equals(status.getRiskStatus())){
//					if("submit".equals(status.getRiskStatus())){
//								list.add(status);
//					}else if("assess".equals(risk.getRiskStatus())){
//						if("submit".equals(status.getRiskStatus())||
//								"assess".equals(status.getRiskStatus())){
//								list.add(status);
//						}
//					}else if("review".equals(risk.getRiskStatus())){
//						if("submit".equals(status.getRiskStatus())||
//								"assess".equals(status.getRiskStatus())||
//								"review".equals(status.getRiskStatus())){
//								list.add(status);
//						}
//					}else if("audit".equals(risk.getRiskStatus())){
//						if("submit".equals(status.getRiskStatus())||
//								"assess".equals(status.getRiskStatus())||
//								"review".equals(status.getRiskStatus())||
//								"audit".equals(status.getRiskStatus())){
//								list.add(status);
//						}
//					}
//				}
//			}
//			
//			row.put("risk", cisErRiskDao.save(risk));
//			row.put("riskStatus", list);
//			resultMap.put(RESULT_ROW, row);
//			resultMap.put(RESULT_CODE, 0);
//			resultMap.put(RESULT_MSG, "获取数据成功");
//		}catch(Exception e){
//			e.printStackTrace();
//			resultMap.put(RESULT_CODE, -1);
//			resultMap.put(RESULT_MSG, "获取数据出错");
//		}
//		return resultMap;
//	}
	
	/**
	 * 详细(回复不过滤) 
	 */
	@Override
	public Map<String, Object> riskSelect(Integer riskId) {
		resetResultMap();
		try{
			CisErRisk risk = cisErRiskDao.findOne(riskId);
			
			Map<String, Object> row = new HashMap<String, Object>();
			List<CisErRiskStatus> list2 = cisErRiskStatusDao.findByRiskId(riskId);
			
			row.put("risk", cisErRiskDao.save(risk));
			row.put("riskStatus", list2);
			resultMap.put(RESULT_ROW, row);
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
	public Map<String, Object> riskSave(CisErRisk cisErRisk) {
		resetResultMap();
		try{
			Integer riskId = cisErRisk.getRiskId();
			
			if(StringUtils.isEmpty(cisErRisk.getDelSign())){
				cisErRisk.setDelSign("N");
				cisErRisk.setRiskStatus("draft");
			}
			
			if(riskId != null){
				CisErRisk risk = cisErRiskDao.findOne(riskId);
				String riskStatus = risk.getRiskStatus();
				
//				if("draft".equals(riskStatus)){
//					risk.setRiskStatus("submit");
//				}else
					
				if("submit".equals(riskStatus)){
					risk.setRiskStatus("assess");
				}else if("assess".equals(riskStatus)){
					risk.setRiskStatus("review");
				}else if("review".equals(riskStatus)){
					risk.setRiskStatus("audit");
				}
				
				resultMap.put(RESULT_ROW, cisErRiskDao.save(risk));
				resultMap.put(RESULT_CODE, 0);
				resultMap.put(RESULT_MSG, "保存成功");
				return resultMap;
			}
			
			if(riskId == null){
				resultMap.put(RESULT_ROW, cisErRiskDao.save(cisErRisk));
				resultMap.put(RESULT_CODE, 0);
				resultMap.put(RESULT_MSG, "保存成功");
				return resultMap;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错");
		}
		return resultMap;
	}
}
