package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmEnterpriseSafety;
import com.nateiot.cis.repository.CisBmEnterpriseSafetyDao;
import com.nateiot.cis.service.CisBmEnterpriseSafetyService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 企业安全
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisBmEnterpriseSafetyService")
@Transactional(readOnly = true)
public class CisBmEnterpriseSafetyServiceImpl extends
         BaseServiceImpl<CisBmEnterpriseSafetyDao, CisBmEnterpriseSafety, Integer> implements
         CisBmEnterpriseSafetyService {
	
	@Autowired
	private CisBmEnterpriseSafetyDao cisBmEnterpriseSafetyDao;
	
	@Autowired
	public CisBmEnterpriseSafetyServiceImpl(CisBmEnterpriseSafetyDao cisBmEnterpriseSafetyDao) {
		super(cisBmEnterpriseSafetyDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer saEnterpriseId){
		resetResultMap();
		try{
			CisBmEnterpriseSafety bean = cisBmEnterpriseSafetyDao.findOne(saEnterpriseId);
			bean.setDelSign("N");
			cisBmEnterpriseSafetyDao.save(bean);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		
		return resultMap;
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> saEnterpriseIds){
		resetResultMap();
		try{
			List<CisBmEnterpriseSafety> list = null;
			if(saEnterpriseIds.size()>0){
				List<CisBmEnterpriseSafety> listModel = cisBmEnterpriseSafetyDao.queryListById(saEnterpriseIds);
				for(CisBmEnterpriseSafety model : listModel){
					model.setDelSign("Y");
				}
				list = listModel;
				resultMap = super.doSave(list);
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}
	
}
