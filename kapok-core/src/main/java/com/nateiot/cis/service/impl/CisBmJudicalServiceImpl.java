package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmJudicialInfo;
import com.nateiot.cis.repository.CisBmJudicalDao;
import com.nateiot.cis.service.CisBmJudicalService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 司法信息
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisBmJudicalService")
@Transactional(readOnly = true)
public class CisBmJudicalServiceImpl extends
         BaseServiceImpl<CisBmJudicalDao, CisBmJudicialInfo, Integer> implements
         CisBmJudicalService {
	
	@Autowired
	private CisBmJudicalDao cisBmJudicalDao;
	
	@Autowired
	public CisBmJudicalServiceImpl(CisBmJudicalDao cisBmJudicalDao) {
		super(cisBmJudicalDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer judicalId){
		resetResultMap();
		try{
			CisBmJudicialInfo bean = cisBmJudicalDao.findOne(judicalId);
			bean.setDelSign("N");
			cisBmJudicalDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> judicalIds){
		resetResultMap();
		try{
			List<CisBmJudicialInfo> list = null;
			if(judicalIds.size()>0){
				List<CisBmJudicialInfo> listModel = cisBmJudicalDao.queryListById(judicalIds);
				for(CisBmJudicialInfo model : listModel){
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
