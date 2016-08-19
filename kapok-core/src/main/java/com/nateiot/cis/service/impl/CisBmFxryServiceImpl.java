package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmFxry;
import com.nateiot.cis.repository.CisBmFxryDao;
import com.nateiot.cis.service.CisBmFxryService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmFxryService")
@Transactional
public class CisBmFxryServiceImpl extends  BaseServiceImpl<CisBmFxryDao, CisBmFxry, Integer> implements CisBmFxryService  {
	
	@Autowired
	private CisBmFxryDao cisBmFxryDao;
	
	@Autowired
	public CisBmFxryServiceImpl(CisBmFxryDao d) {
		super(d);
	}
	
	/*
	 * 查询 存在 服刑 人员信息(non-Javadoc)
	 * 
	 */
	@Override
	public Map<String,Object>  getFxryHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmFxryDao.getFxryHouseHolder(conditions, pageable);
			
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
	 * 软删除
	 */
	@Override
	@Transactional
	public Map<String,Object> softDel(int householderId){
		resetResultMap();
		try{
			CisBmFxry bean = cisBmFxryDao.findByHouseholderId(householderId);
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
	
	/**
	 * 硬删除
	 */
	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer householderId) {
		resetResultMap();
		CisBmFxry bean =null ;
		try {
			bean =cisBmFxryDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getFxId());
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

	/**
	 * 根据householderId找到对应人员信息记录
	 */
	@Override
	public  CisBmFxry getCisBmFxryByHouseholderId(int householderId) {
		return cisBmFxryDao.findByHouseholderId(householderId);
	}

}
