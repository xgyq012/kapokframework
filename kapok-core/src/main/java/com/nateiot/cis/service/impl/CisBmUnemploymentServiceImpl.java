package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCultivate;
import com.nateiot.cis.domain.CisBmUnemployment;
import com.nateiot.cis.repository.CisBmUnemploymentDao;
import com.nateiot.cis.service.CisBmUnemploymentService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 失业证办理
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmUnemploymentService")
@Transactional(readOnly = true)
public class CisBmUnemploymentServiceImpl extends
         BaseServiceImpl<CisBmUnemploymentDao, CisBmUnemployment, Integer> implements
         CisBmUnemploymentService {
	
	@Autowired
	private CisBmUnemploymentDao cisBmUnemploymentDao;
	
	@Autowired
	public CisBmUnemploymentServiceImpl(CisBmUnemploymentDao cisBmUnemploymentDao) {
		super(cisBmUnemploymentDao);
	}
	
	/*@Override
	public Map<String, Object> softDel(Integer unemploymentId){
		resetResultMap();
		try{
			//CisBmUnemployment bean = cisBmUnemploymentDao.findOne(unemploymentId);
			//bean.setDelSign("N");
			//cisBmUnemploymentDao.save(bean);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		
		return resultMap;
	}
	*/
	/*@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> unemploymentIds){
		resetResultMap();
		try{
			List<CisBmUnemployment> list = null;
			if(unemploymentIds.size()>0){
				List<CisBmUnemployment> listModel = cisBmUnemploymentDao.queryListById(unemploymentIds);
				for(CisBmUnemployment model : listModel){
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
	}*/
	
}
