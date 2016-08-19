package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmDieinfo;
import com.nateiot.cis.domain.CisBmHandicappedPeople;
import com.nateiot.cis.repository.CisBmHandicappedPeopleDao;
import com.nateiot.cis.service.CisBmHandicappedPeopleService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmHandicappedPeopleService")
@Transactional
public class CisBmHandicappedPeopleServiceImpl extends BaseServiceImpl<CisBmHandicappedPeopleDao, CisBmHandicappedPeople, Integer> implements
CisBmHandicappedPeopleService {
	
	@Autowired
	private CisBmHandicappedPeopleDao cisBmHandicappedPeopleDao;

	@Autowired
	public CisBmHandicappedPeopleServiceImpl(CisBmHandicappedPeopleDao d) {
		super(d);
	}
	
	
	/*
	 * 查询 存在 残疾人 人员信息(non-Javadoc)
	 * 
	 */
	@Override
	public Map<String,Object>  getHandicappedPeopleHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmHandicappedPeopleDao.getHandicappedPeopleHouseHolder(conditions, pageable);
			
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
			CisBmHandicappedPeople bean = cisBmHandicappedPeopleDao.findByHouseholderId(householderId);
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
		CisBmHandicappedPeople bean =null ;
		try {
			bean =cisBmHandicappedPeopleDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.gethId());
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
	public  CisBmHandicappedPeople getCisBmHandicappedPeopleByHouseholderId(int householderId) {
		return cisBmHandicappedPeopleDao.findByHouseholderId(householderId);
	}

}











