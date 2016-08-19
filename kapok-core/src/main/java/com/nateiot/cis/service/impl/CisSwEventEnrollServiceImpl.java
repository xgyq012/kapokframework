package com.nateiot.cis.service.impl;

import com.nateiot.base.common.SessionUtil;
import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.base.repository.GxwlSysUserDao;
import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisSwEnrollRowTable;
import com.nateiot.cis.domain.CisSwEventEnroll;
import com.nateiot.cis.domain.CisSwEventFile;
import com.nateiot.cis.repository.CisSwEnrollRowTableDao;
import com.nateiot.cis.repository.CisSwEventEnrollDao;
import com.nateiot.cis.repository.CisSwEventFileDao;
import com.nateiot.cis.service.CisEsIntegalDetailService;
import com.nateiot.cis.service.CisSwEnrollRowTableService;
import com.nateiot.cis.service.CisSwEventEnrollService;
import com.nateiot.cis.service.CisSwEventFileService;
import com.nateiot.core.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务办事 -- 事件登记
 * 
 *  @author Guohw
 */
@Service(value = "cisSwEventEnrollService")
@Transactional(readOnly=true)
public class CisSwEventEnrollServiceImpl extends 
		BaseServiceImpl<CisSwEventEnrollDao, CisSwEventEnroll, Integer>
		implements CisSwEventEnrollService{

	@Autowired
	private CisSwEventEnrollDao cisSwEventEnrollDao;
	
	@Autowired
	private CisSwEventFileService cisSwEventFileService;
	
	@Autowired
	private CisSwEventFileDao cisSwEventFileDao;
	
	@Autowired
	private GxwlSysUserDao gxwlSysUserDao;
	
	@Autowired
	private GxwlSysDocDao gxwlSysDocDao;
	
	@Autowired
	private GxwlSysDocService gxwlSysDocService;
	
	@Autowired
	private CisSwEnrollRowTableDao cisSwEnrollRowTableDao;
	
	@Autowired
	private CisSwEnrollRowTableService cisSwEnrollRowTableService;
	
	@Autowired
	private CisEsIntegalDetailService cisEsIntegalDetailService;
	
	@Autowired
	public CisSwEventEnrollServiceImpl(CisSwEventEnrollDao d) {
		super(d);
	}
	
	/**
	 * 查询 
	 */
	@Override
	public Map<String, Object> enrollSelect(Integer enrollId) {
		resetResultMap();
		try{
			CisSwEventEnroll bean = cisSwEventEnrollDao.findOne(enrollId);
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("enroll", cisSwEventEnrollDao.save(bean));
			row.put("rowTable", cisSwEnrollRowTableDao.findByEnrollId(enrollId));
			resultMap.put(RESULT_ROW, row);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		return resultMap;
	}
	
	/**
	 * 保存 
	 */
	@Override
	@Transactional
	public Map<String, Object> enrollSave(CisSwEventEnroll bean,
			String root) {
		resetResultMap();
		try{
			Integer enrollId = bean.getEnrollId();
			Integer userId = SessionUtil.getCurrentUser().getUserId();
			CisSwEventEnroll sub = enrollId == null ? null : cisSwEventEnrollDao.findOne(enrollId);
//			sub.setScoreDetail(bean.getScoreDetail() == null ? null : bean.getScoreDetail());
			
			//评价
			if("transact".equals(bean.getEnrollStatus())){
				bean.setEnrollStatus("estimate");
				bean.setEstimateId(userId);
				bean.setTransactId(sub.getTransactId());
				bean.setSubmitId(sub.getSubmitId());
				bean.setSignForId(sub.getSignForId());
				bean.setLon(sub.getLon());
				bean.setLat(sub.getLat());
				cisSwEnrollRowTableService.estimate(bean.getEnrollId(), 
						bean.getSuggestion(),bean.getUnitsId(), bean.getEnrollStatus());
				bean.setTransactTime(new Date());
			}
			//处理
			if("signFor".equals(bean.getEnrollStatus())){
				bean.setEnrollStatus("transact");
				bean.setTransactId(userId);
				bean.setSubmitId(sub.getSubmitId());
				bean.setSignForId(sub.getSignForId());
				bean.setLon(sub.getLon());
				bean.setLat(sub.getLat());
				cisSwEnrollRowTableService.estimate(bean.getEnrollId(), 
						bean.getSuggestion(),bean.getUnitsId(), bean.getEnrollStatus());
				bean.setTransactTime(new Date());
				cisEsIntegalDetailService.scoreDetail(userId, "endScore", new Date(), 
						null, null, null);
			}
			
			//新增
			if(enrollId == null){
				CisSwEventEnroll entity = cisSwEventEnrollDao.save(bean);
				bean.setEnrollStatus("draft");
				if(userId != null){
					GxwlSysUser user = gxwlSysUserDao.findOne(userId);
					bean.setCreateByName(user.getRealname());
				}
				if(bean.getCisSwEventFile() != null){
					List<CisSwEventFile> eventFile = bean.getCisSwEventFile();
					for(CisSwEventFile file : eventFile){
						file.setEnrollId(entity.getEnrollId());
					}
				}
			}else{
				CisSwEventEnroll d = cisSwEventEnrollDao.findOne(enrollId);
				List<CisSwEventFile> olds = d.getCisSwEventFile();
				List<CisSwEventFile> news = bean.getCisSwEventFile();
				if(news == null){
					cisSwEventFileDao.deleteInBatch(olds);
				}else{
					for(CisSwEventFile file : olds){
						if(!news.contains(file)){
							cisSwEventFileDao.delete(file);
						}
					}
					for(CisSwEventFile file : news){
						file.setEnrollId(enrollId);
						gxwlSysDocService.moveDoc(file.getDocId());
					}
				}
			}
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("enroll", cisSwEventEnrollDao.save(bean));
			row.put("rowTable", cisSwEnrollRowTableDao.findByEnrollId(bean.getEnrollId()));
			resultMap.put(RESULT_ROW, row);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存失败");
		}
		return resultMap;
	}
	
	/**
	 * 删除  
	 */
	@Override
	@Transactional
	public Map<String, Object> enrollDelete(Integer enrollId) {
		resetResultMap();
		try{
			CisSwEventEnroll eventEnroll = cisSwEventEnrollDao.findOne(enrollId);
			eventEnroll.setDelSign("Y");
			List<CisSwEventFile> fileList = eventEnroll.getCisSwEventFile();
			for(CisSwEventFile file : fileList){
				GxwlSysDoc doc = gxwlSysDocDao.findOne(file.getDocId());
				doc.setIsTemp("Y");
				if(enrollId.equals(file.getEnrollId())){
					cisSwEventFileDao.delete(file);
				}
			}
			List<CisSwEnrollRowTable> rowTable = cisSwEnrollRowTableDao.findByEnrollId(enrollId);
			for(CisSwEnrollRowTable table : rowTable){
				if(enrollId.equals(table.getEnrollId())){
					cisSwEnrollRowTableDao.delete(table);
				}
			}
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功 ");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}

	/**
	 * 事件类别下拉框 
	 */
	@Override
	public Map<String, Object> comboBox(String eventType) {
		resetResultMap();
		try{
			List<Map<String, Object>> list = cisSwEventEnrollDao.findTheTypeName(eventType);
			resultMap.put(RESULT_ROWS, list);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功 ");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}

//	/**
//	 * 按钮权限判断 
//	 */
//	@Override
//	public Map<String, Object> permission(Integer enrollId) {
//		resetResultMap();
//		try{
//			
//			resultMap.put(RESULT_ROWS, null);
//			resultMap.put(RESULT_CODE, 0);
//			resultMap.put(RESULT_MSG, "删除成功 ");
//		}catch(Exception e){
//			e.printStackTrace();
//			resultMap.put(RESULT_CODE, -1);
//			resultMap.put(RESULT_MSG, "删除出错");
//		}
//		return resultMap;
//	}
	
}