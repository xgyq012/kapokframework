package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmDieinfo;
import com.nateiot.cis.repository.CisBmDieinfoDao;
import com.nateiot.cis.service.CisBmDieinfoService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value = "CisBmDieinfoService")
@Transactional
public class CisBmDieinfoServiceImpl  extends BaseServiceImpl<CisBmDieinfoDao, CisBmDieinfo, Integer> implements CisBmDieinfoService {

	@Autowired
	private CisBmDieinfoDao cisBmDieinfoDao;
	
	@Autowired
	public CisBmDieinfoServiceImpl(CisBmDieinfoDao d) {
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
			CisBmDieinfo bean = cisBmDieinfoDao.findByHouseholderId(householderId);
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
		CisBmDieinfo bean =null ;
		try {
			bean =cisBmDieinfoDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getDieId());
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
	public  CisBmDieinfo getCisBmDieinfoByHouseholderId(int householderId) {
		return cisBmDieinfoDao.findByHouseholderId(householderId);
	}


}
