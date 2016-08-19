package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmOrphan;
import com.nateiot.cis.repository.CisBmOrphanDao;
import com.nateiot.cis.service.CisBmOrphanService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmOrphanService")
@Transactional
public class  CisBmOrphanServiceImpl  extends BaseServiceImpl<CisBmOrphanDao, CisBmOrphan, Integer> implements CisBmOrphanService {
	
	@Autowired
	private CisBmOrphanDao cisBmOrphanDao;
	
	@Autowired
	public CisBmOrphanServiceImpl(CisBmOrphanDao d) {
		super(d);
	}
	
	/*
	 * 查询 存在 违法青少年 人员信息(non-Javadoc)
	 * 
	 */
	@Override
	public Map<String,Object>  getOrphanHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmOrphanDao.getOrphanHouseHolder(conditions, pageable);
			
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
			CisBmOrphan bean = cisBmOrphanDao.findByHouseholderId(householderId);
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
		CisBmOrphan bean =null ;
		try {
			bean =cisBmOrphanDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getOrphanId());
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
	public  CisBmOrphan getCisBmOrphanByHouseholderId(int householderId) {
		return cisBmOrphanDao.findByHouseholderId(householderId);
	}

 
}
