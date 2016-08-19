package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmXdry;
import com.nateiot.cis.repository.CisBmXdryDao;
import com.nateiot.cis.service.CisBmXdryService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmXdryService")
@Transactional
public class CisBmXdryServiceImpl extends BaseServiceImpl<CisBmXdryDao, CisBmXdry, Integer> implements CisBmXdryService {

	@Autowired
	private CisBmXdryDao cisBmXdryDao;
	
	@Autowired
	public CisBmXdryServiceImpl(CisBmXdryDao d) {
		super(d);
	}

	
	

	/**
	 * 软删除
	 */
	@Override
	@Transactional
	public Map<String,Object> softDel(int householderId){
		resetResultMap();
		try{
			CisBmXdry bean = cisBmXdryDao.findByHouseholderId(householderId);
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
		CisBmXdry bean =null ;
		try {
			bean =cisBmXdryDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getXdId());
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
	public  CisBmXdry getCisBmXdryByHouseholderId(int householderId) {
		return cisBmXdryDao.findByHouseholderId(householderId);
	}



	/*
	 * 查询 存在 吸毒 人员信息(non-Javadoc)
	 * 
	 */
	@Override
	public Map<String, Object> getXdryHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmXdryDao.getXdryHouseHolder(conditions, pageable);
			
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

}
