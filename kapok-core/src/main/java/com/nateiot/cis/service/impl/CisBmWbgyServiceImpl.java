package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmComfort;
import com.nateiot.cis.domain.CisBmWbgy;
import com.nateiot.cis.repository.CisBmWbgyDao;
import com.nateiot.cis.service.CisBmWbgyService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmWbgyService")
@Transactional
public class CisBmWbgyServiceImpl extends BaseServiceImpl<CisBmWbgyDao, CisBmWbgy, Integer> implements CisBmWbgyService {

	@Autowired
	private CisBmWbgyDao cisBmWbgyDao;
	
	@Autowired
	public CisBmWbgyServiceImpl(CisBmWbgyDao d) {
		super(d);
	}

	@Override
	public CisBmWbgy getCisBmWbgyByHouseholderId(int householderId) {
		return cisBmWbgyDao.findByHouseholderId(householderId);
	}
	
	/*
	 * 查询 存在 五保供养 的 人员信息(non-Javadoc)
	 * @see com.nateiot.cis.service.CisBmWbgyService#getWbgyHouseHolder(java.util.Map, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Map<String,Object>  getWbgyHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmWbgyDao.getWbgyHouseHolder(conditions, pageable);
			
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
		CisBmWbgy bean =null ;
		try {
			bean =cisBmWbgyDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getWbId());
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
			CisBmWbgy bean = cisBmWbgyDao.findByHouseholderId(householderId);
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
