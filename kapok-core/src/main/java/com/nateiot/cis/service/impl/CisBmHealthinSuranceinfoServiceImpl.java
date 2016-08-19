package com.nateiot.cis.service.impl;

import com.nateiot.cis.domain.CisBmHealthinSuranceinfo;
import com.nateiot.cis.repository.CisBmHealthinSuranceinfoDao;
import com.nateiot.cis.service.CisBmHealthinSuranceinfoService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;
import com.nateiot.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 
 * @author xiaguangjun
 *
 */
@Service("cisBmHealthinSuranceinfoService")
@Transactional
public class CisBmHealthinSuranceinfoServiceImpl
		extends BaseServiceImpl<CisBmHealthinSuranceinfoDao, CisBmHealthinSuranceinfo, Integer> implements
		CisBmHealthinSuranceinfoService, BaseService<CisBmHealthinSuranceinfoDao, CisBmHealthinSuranceinfo, Integer> {
	
	@Autowired
	private CisBmHealthinSuranceinfoDao cisBmHealthinSuranceinfoDao;
	
	@Autowired
	public CisBmHealthinSuranceinfoServiceImpl(CisBmHealthinSuranceinfoDao d) {
		super(d);
	}


	@Override
	public Map<String,Object> getCisBmHealthinSuranceMsg(int householderId){
		resetResultMap();
		try {
			CisBmHealthinSuranceinfo model =  cisBmHealthinSuranceinfoDao.findByHouseholderId(householderId);
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
	public Map<String, Object> searchHolderHealthSuranceinfo(Map<String, SearchFilter> conditions, Pageable pageable){
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisBmHealthinSuranceinfoDao.searchHolderHealthInsurance(conditions,pageable);
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
			CisBmHealthinSuranceinfo bean = cisBmHealthinSuranceinfoDao.findByHouseholderId(householderId);
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
		CisBmHealthinSuranceinfo bean =null ;
		try {
			bean =cisBmHealthinSuranceinfoDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getHealthId());
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
	public CisBmHealthinSuranceinfo getCisBmHealthinSuranceinfoByHouseholderId(int householderId) {
		return cisBmHealthinSuranceinfoDao.findByHouseholderId(householderId);
	}

}
