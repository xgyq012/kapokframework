package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmXsjjry;
import com.nateiot.cis.repository.CisBmXsjjryDao;
import com.nateiot.cis.service.CisBmXsjjryService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Transactional
@Service("cisBmXsjjryService")
public class CisBmXsjjryServiceImpl extends BaseServiceImpl<CisBmXsjjryDao, CisBmXsjjry, Integer> implements CisBmXsjjryService {

	@Autowired
	private CisBmXsjjryDao  cisBmXsjjryDao;
	
	@Autowired
	public CisBmXsjjryServiceImpl(CisBmXsjjryDao d) {
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
			CisBmXsjjry bean = cisBmXsjjryDao.findByHouseholderId(householderId);
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
		CisBmXsjjry bean =null ;
		try {
			bean =cisBmXsjjryDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getxId());
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
	public  CisBmXsjjry getCisBmXsjjryByHouseholderId(int householderId) {
		return cisBmXsjjryDao.findByHouseholderId(householderId);
	}

}
