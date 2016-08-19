package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCqsfssry;
import com.nateiot.cis.repository.CisBmCqsfssryDao;
import com.nateiot.cis.service.CisBmCqsfssryService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmCqsfssryService")
@Transactional
public class CisBmCqsfssryServiceImpl extends BaseServiceImpl<CisBmCqsfssryDao, CisBmCqsfssry , Integer> implements CisBmCqsfssryService {

	@Autowired
	private CisBmCqsfssryDao cisBmCqsfssryDao;
	
	@Autowired
	public CisBmCqsfssryServiceImpl(CisBmCqsfssryDao d) {
		super(d);
	}

	@Override
	public CisBmCqsfssry getCisBmCqsfssryByHouseholderId(int householderId) {
		return  cisBmCqsfssryDao.findByHouseholderId(householderId);
	}
	
	/**
	 * 硬删除
	 */
	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer householderId) {
		resetResultMap();
		CisBmCqsfssry bean =null ;
		try {
			bean =cisBmCqsfssryDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getCfId());
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
			CisBmCqsfssry bean = cisBmCqsfssryDao.findByHouseholderId(householderId);
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
