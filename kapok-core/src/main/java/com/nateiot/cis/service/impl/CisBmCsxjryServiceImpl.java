package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmComfort;
import com.nateiot.cis.domain.CisBmCsxjry;
import com.nateiot.cis.repository.CisBmCsxjryDao;
import com.nateiot.cis.service.CisBmCsxjryService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 从事邪教人员
 * @author xiaguangjun
 *
 */
@Service("cisBmCsxjryService")
@Transactional
public class CisBmCsxjryServiceImpl extends BaseServiceImpl<CisBmCsxjryDao, CisBmCsxjry , Integer> implements CisBmCsxjryService {

	
	@Autowired
	private CisBmCsxjryDao cisBmCsxjryDao;
	
	@Autowired
	public CisBmCsxjryServiceImpl(CisBmCsxjryDao d) {
		super(d);
	}

	
	/*
	 * 查询 存在 从事邪教人员 人员信息(non-Javadoc)
	 * 
	 */
	@Override
	public Map<String,Object>  getCsxjryHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmCsxjryDao.getCsxjryHouseHolder(conditions, pageable);
			
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
	public CisBmCsxjry getCisBmCsxjryByHouseholderId(int householderId) {
		return  cisBmCsxjryDao.findByHouseholderId(householderId);
	}
	
	/**
	 * 硬删除
	 */
	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer householderId) {
		resetResultMap();
		CisBmCsxjry bean =null ;
		try {
			bean =cisBmCsxjryDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getXjId());
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
			CisBmCsxjry bean = cisBmCsxjryDao.findByHouseholderId(householderId);
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
