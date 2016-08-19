package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmLeftoverChildren;
import com.nateiot.cis.repository.CisBmLeftoverChildrenDao;
import com.nateiot.cis.service.CisBmLeftoverChildrenService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmLeftoverChildrenService")
@Transactional
public class CisBmLeftoverChildrenServiceImpl  extends BaseServiceImpl<CisBmLeftoverChildrenDao, CisBmLeftoverChildren, Integer> implements CisBmLeftoverChildrenService {
	
	@Autowired
	private CisBmLeftoverChildrenDao cisBmLeftoverChildrenDao;
	
	@Autowired
	public CisBmLeftoverChildrenServiceImpl(CisBmLeftoverChildrenDao d) {
		super(d);
	}
	

	/*
	 * 查询 存在 留守儿童的 人员信息(non-Javadoc)
	 * @see com.nateiot.cis.service.CisBmWbgyService#getWbgyHouseHolder(java.util.Map, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Map<String,Object>  getLeftoverChildren(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmLeftoverChildrenDao.getLeftoverChildrenHouseHolder(conditions, pageable);
			
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
			CisBmLeftoverChildren bean = cisBmLeftoverChildrenDao.findByHouseholderId(householderId);
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
		CisBmLeftoverChildren bean =null ;
		try {
			bean =cisBmLeftoverChildrenDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getChildrenId());
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
	public  CisBmLeftoverChildren getCisBmLeftoverChildrenByHouseholderId(int householderId) {
		return cisBmLeftoverChildrenDao.findByHouseholderId(householderId);
	}
 
	

}
