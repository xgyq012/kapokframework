package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmWfjl;
import com.nateiot.cis.repository.CisBmWfjlDao;
import com.nateiot.cis.service.CisBmWfjlService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Transactional
@Service("cisBmWfjlService")
public class CisBmWfjlServiceImpl extends BaseServiceImpl<CisBmWfjlDao, CisBmWfjl, Integer> implements CisBmWfjlService {

	@Autowired
	private CisBmWfjlDao cisBmWfjlDao;
	
	@Autowired
	public CisBmWfjlServiceImpl(CisBmWfjlDao d) {
		super(d);
	}
	
	/*
	 * 查询 存在 违法青少年 人员信息(non-Javadoc)
	 * 
	 */
	@Override
	public Map<String,Object>  getWfqsnInfo(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmWfjlDao.getWfqsnInfoHouseHolder(conditions, pageable);
			
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
			CisBmWfjl bean = cisBmWfjlDao.findByHouseholderId(householderId);
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
		CisBmWfjl bean =null ;
		try {
			bean =cisBmWfjlDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getwId());
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
	public  CisBmWfjl getCisBmWfjlByHouseholderId(int householderId) {
		return cisBmWfjlDao.findByHouseholderId(householderId);
	}

}
