package com.nateiot.cis.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.common.SessionUtil;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.repository.GxwlSysUserDao;
import com.nateiot.cis.domain.CisSwEnrollRowTable;
import com.nateiot.cis.domain.CisSwEventEnroll;
import com.nateiot.cis.repository.CisSwEnrollRowTableDao;
import com.nateiot.cis.repository.CisSwEventEnrollDao;
import com.nateiot.cis.service.CisEsIntegalDetailService;
import com.nateiot.cis.service.CisSwEnrollRowTableService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 *  服务办事 -- 事件登记行表
 *  
 *  @author Guohw
 */
@Service(value = "cisSwEnrollRowTableService")
@Transactional(readOnly=true)
public class CisSwEnrollRowTableServiceImpl extends 
		BaseServiceImpl<CisSwEnrollRowTableDao, CisSwEnrollRowTable, Integer>
		implements CisSwEnrollRowTableService{

	@Autowired
	private CisSwEnrollRowTableDao cisSwEnrollRowTableDao;
	
	@Autowired
	private GxwlSysUserDao gxwlSysUserDao;
	
	@Autowired
	private CisSwEventEnrollDao cisSwEventEnrollDao;
	
	@Autowired
	private CisEsIntegalDetailService cisEsIntegalDetailService;
	
	@Autowired
	public CisSwEnrollRowTableServiceImpl(CisSwEnrollRowTableDao d) {
		super(d);
	}

	/**
	 * 保存 
	 */
	@Override
	@Transactional
	public Map<String, Object> rowTableSave(CisSwEnrollRowTable bean) {
		resetResultMap();
		try{
			Integer enrollId = bean.getEnrollId();
			CisSwEventEnroll entity = cisSwEventEnrollDao.findByEnrollId(enrollId);
			entity.setScoreDetail(bean.getScoreDetail());
			Integer userId = SessionUtil.getCurrentUser().getUserId();
			if("draft".equals(entity.getEnrollStatus())){
				entity.setSubmitId(userId);
				entity.setEnrollStatus(bean.getProcess());
				entity.setTransactTime(new Date());
				entity.setUnitsId(bean.getBackLogId());
				cisEsIntegalDetailService.scoreDetail(userId, entity.getScoreDetail(), 
						new Date(), null, null, null);
			}else if("appearIn".equals(entity.getEnrollStatus())||"assign".equals(entity.getEnrollStatus())){
				entity.setSignForId(userId);
				entity.setEnrollStatus(bean.getProcess());
				entity.setTransactTime(new Date());
			}
			
			if("sendBack".equals(bean.getProcess())){
				entity.setEnrollStatus("draft");
			}
			
			resultMap.put(RESULT_ROW, cisSwEnrollRowTableDao.save(bean));
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
	 * 评价/处理
	 */
	@Override
	@Transactional
	public Map<String, Object> estimate(Integer enrollId, String remark,
			Integer backLogId, String enrollStatus) {
		resetResultMap();
		try{
			CisSwEventEnroll enroll = cisSwEventEnrollDao.findOne(enrollId);
			Integer userId = SessionUtil.getCurrentUser().getUserId();
			GxwlSysUser user = gxwlSysUserDao.findOne(userId);
			CisSwEnrollRowTable rowTable = new CisSwEnrollRowTable();
			rowTable.setEnrollId(enrollId);
			rowTable.setBackLogId(backLogId);
			rowTable.setRemark(remark);
			rowTable.setCreaterId(userId);
			rowTable.setCreateBy(user);
			rowTable.setCreateTime(new Date());
			rowTable.setProcess(enrollStatus);
//			rowTable.setOperate(user.getRealname());
			rowTable.setOperateId(userId);
			
			cisEsIntegalDetailService.scoreDetail(userId, enroll.getScoreDetail(), 
					new Date(), null, null, null);
			
			resultMap.put(RESULT_ROW, cisSwEnrollRowTableDao.save(rowTable));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存失败");
		}
		return null;
	}

}
