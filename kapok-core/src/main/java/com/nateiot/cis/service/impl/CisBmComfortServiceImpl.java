package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmComfort;
import com.nateiot.cis.repository.CisBmComfortDao;
import com.nateiot.cis.service.CisBmComfortService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmComfortService")
@Transactional
public class CisBmComfortServiceImpl extends BaseServiceImpl<CisBmComfortDao, CisBmComfort, Integer> implements CisBmComfortService {
	
	@Autowired
	private CisBmComfortDao cisBmComfortDao;
	
	@Autowired
	public CisBmComfortServiceImpl(CisBmComfortDao d) {
		super(d);
	}

	@Override
	public CisBmComfort getCisBmComfortByHouseholderId(int householderId) {
		return  cisBmComfortDao.findByHouseholderId(householderId);
	}
	
	/*
	 * 查询 存在优抚 人员信息(non-Javadoc)
	 * 
	 */
	@Override
	public Map<String,Object>  getComfortHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmComfortDao.getComfortHouseHolder(conditions, pageable);
			
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询失败。");
		}
		return resultMap;
	}
	
	
	/**
	 * 硬删除
	 */
	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer householderId) {
		resetResultMap();
		CisBmComfort bean =null ;
		try {
			bean =cisBmComfortDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getComfortId());
			}else{
				resultMap.put(RESULT_CODE, -1);
				resultMap.put(RESULT_MSG, "数据不存在！");
			}
		} catch (Exception e) {
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
			e.printStackTrace();
		}
		return resultMap;
	}


	@Override
	public Map<String, Object> softDel(int householderId) {
		
		resetResultMap();
		try{
			CisBmComfort bean = cisBmComfortDao.findByHouseholderId(householderId);
			bean.setDelSign("Y");
			super.doSave(bean);
			resultMap.put(RESULT_CODE,0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}


}
