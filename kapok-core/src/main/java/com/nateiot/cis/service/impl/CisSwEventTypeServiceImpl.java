package com.nateiot.cis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.common.SessionUtil;
import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisSwEventSlave;
import com.nateiot.cis.domain.CisSwEventType;
import com.nateiot.cis.repository.CisSwEventSlaveDao;
import com.nateiot.cis.repository.CisSwEventTypeDao;
import com.nateiot.cis.service.CisSwEventTypeService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;


/**
 * 服务办事 -- 事件类型管理
 * 
 *  @author Guohw
 */
@Service(value = "cisSwEventTypeService")
@Transactional(readOnly = true)
public class CisSwEventTypeServiceImpl extends 
		BaseServiceImpl<CisSwEventTypeDao, CisSwEventType, Integer>
		implements CisSwEventTypeService{
	
	@Autowired
	private CisSwEventTypeDao cisSwEventTypeDao;
	
	@Autowired
	private CisSwEventSlaveDao cisSwEventSlaveDao;
	
	@Autowired
	private GxwlSysDocService gxwlSysDocService;
	
	@Autowired
	public CisSwEventTypeServiceImpl(CisSwEventTypeDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> eventSave(CisSwEventType entity,
			String root) {
		resetResultMap();
		try{
			Integer typeId = entity.getTypeId();
			//新增
			if(typeId == null){
				entity.setDelSign("N");
				CisSwEventType bean = cisSwEventTypeDao.save(entity);
				List<CisSwEventSlave> cisSwEventSlave = entity.getCisSwEventSlave();
				if(cisSwEventSlave != null){
					for(CisSwEventSlave slave : cisSwEventSlave){
						slave.setTypeId(bean.getTypeId());
					}
				}
			}else{
				CisSwEventType d = cisSwEventTypeDao.findOne(typeId);
				List<CisSwEventSlave> olds = d.getCisSwEventSlave();
				List<CisSwEventSlave> news = entity.getCisSwEventSlave();
				
				if(news == null){
					cisSwEventSlaveDao.deleteInBatch(olds);
				}else{
					for(CisSwEventSlave slave : olds){
						if(!news.contains(slave)){
							cisSwEventSlaveDao.delete(slave);
						}
					}
					for(CisSwEventSlave slave2 : news){
						slave2.setTypeId(typeId);
					}
				}
			}
			resultMap.put(RESULT_ROW, cisSwEventTypeDao.save(entity));
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
	 * 服务办事工作台列表 
	 */
	@Override
	public Map<String, Object> getData(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			Integer userId = SessionUtil.getCurrentUser().getUserId();
			Page<Map<String, Object>> page = cisSwEventTypeDao.findTheToDoList(conditions, pageable, userId);
			
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "获取数据成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "获取数据出错");
		}
		return resultMap;
	}

}
