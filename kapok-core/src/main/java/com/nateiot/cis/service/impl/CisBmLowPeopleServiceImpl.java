package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmDieinfo;
import com.nateiot.cis.domain.CisBmLowPeople;
import com.nateiot.cis.repository.CisBmLowPeopleDao;
import com.nateiot.cis.service.CisBmLowPeopleService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 低保人员
 * @author xiaguangjun
 *
 */
@Service(value = "cisBmLowPeopleService")
@Transactional
public class CisBmLowPeopleServiceImpl   extends BaseServiceImpl<CisBmLowPeopleDao, CisBmLowPeople, Integer> implements CisBmLowPeopleService {

	@Autowired
	private CisBmLowPeopleDao cisBmLowPeopleDao;
	 
	@Autowired
	public CisBmLowPeopleServiceImpl(CisBmLowPeopleDao d) {
		super(d);
	}

	@Override
	public CisBmLowPeople getCisBmCisBmLowPeopleByHouseholderId(int householderId) {
		return cisBmLowPeopleDao.findByHouseholderId(householderId);
	}
	
	/*
	 * 查询 存在 低保 人员信息(non-Javadoc)
	 * 
	 */
	@Override
	public Map<String,Object>  getLowPeopleHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmLowPeopleDao.getLowPeopleHouseHolder(conditions, pageable);
			
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
	

	@Override
	public Map<String, Object> softDel(int householderId) {
		resetResultMap();
		try{
			CisBmLowPeople bean = cisBmLowPeopleDao.findByHouseholderId(householderId);
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
		CisBmLowPeople bean =null ;
		try {
			bean =cisBmLowPeopleDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getDbId());
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


}