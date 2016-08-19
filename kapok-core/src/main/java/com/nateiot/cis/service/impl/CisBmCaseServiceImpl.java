package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCaseInfo;
import com.nateiot.cis.repository.CisBmCaseDao;
import com.nateiot.cis.service.CisBmCaseService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 案发情况
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmCaseService")
@Transactional(readOnly = true)
public class CisBmCaseServiceImpl extends
         BaseServiceImpl<CisBmCaseDao, CisBmCaseInfo, Integer> implements
         CisBmCaseService {
	
	@Autowired
	private CisBmCaseDao cisBmCaseDao;
	
	@Autowired
	public CisBmCaseServiceImpl(CisBmCaseDao cisBmCaseDao) {
		super(cisBmCaseDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer caseId){
		resetResultMap();
		try{
			CisBmCaseInfo bean = cisBmCaseDao.findOne(caseId);
			bean.setDelSign("N");
			cisBmCaseDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> caseIds){
		resetResultMap();
		try{
			List<CisBmCaseInfo> list = null;
			if(caseIds.size()>0){
				List<CisBmCaseInfo> listModel = cisBmCaseDao.queryListById(caseIds);
				for(CisBmCaseInfo model : listModel){
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
