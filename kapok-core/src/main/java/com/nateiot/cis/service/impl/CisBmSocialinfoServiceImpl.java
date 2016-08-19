package com.nateiot.cis.service.impl;


import com.nateiot.cis.domain.CisBmSocialinfo;
import com.nateiot.cis.repository.CisBmSocialinfoDao;
import com.nateiot.cis.service.CisBmSocialinfoService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service(value = "cisBmSocialinfoService")
@Transactional 
public class CisBmSocialinfoServiceImpl  extends BaseServiceImpl<CisBmSocialinfoDao, CisBmSocialinfo, Integer> implements
CisBmSocialinfoService {
	
	@Autowired
	private CisBmSocialinfoDao cisBmSocialinfoDao ;
	
	@Autowired
	public CisBmSocialinfoServiceImpl(CisBmSocialinfoDao d) {
		super(d);
	}

	@Override
	public CisBmSocialinfo getCisBmSocialinfoByHouseholderId(int householderId){
		
		return cisBmSocialinfoDao.findByHouseholderId(householderId);
	}

	@Override
	public Map<String,Object> getCisBmSocialinfoByholderIdMsg(int householderId){
		resetResultMap();
		try {
			CisBmSocialinfo model =  cisBmSocialinfoDao.findByHouseholderId(householderId);
			if(model!=null){
				resultMap.put(RESULT_ROW,model);
				resultMap.put(RESULT_CODE, 0);
				resultMap.put(RESULT_MSG, "查询成功");
			}else {
				resultMap.put(RESULT_CODE,1);
				resultMap.put(RESULT_MSG, "数据不存在");
			}
		} catch (Exception e) {
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> searchHolderSocialinfo(Map<String, SearchFilter> conditions, Pageable pageable){
		resetResultMap();
		try{
			Page<Map<String, Object>>  page = cisBmSocialinfoDao.searchHolderSocialinfo(conditions,pageable);
			resultMap.put(RESULT_ROWS,page.getContent());
			resultMap.put(RESULT_TOTAL,page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询失败");
		}

		return 	resultMap;
	}
	
	/**
	 * 软删除
	 */
	@Override
	@Transactional
	public Map<String,Object> softDel(int householderId){
		resetResultMap();
		try{
			CisBmSocialinfo bean = cisBmSocialinfoDao.findByHouseholderId(householderId);
			bean.setDelSign("Y");
			super.doSave(bean);
			resultMap.put(RESULT_CODE, 0);
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
		CisBmSocialinfo bean =null ;
		try {
			bean = cisBmSocialinfoDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getSocialId());
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
