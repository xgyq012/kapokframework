package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmDuryroomInfo;
import com.nateiot.cis.repository.CisBmDuryroomDao;
import com.nateiot.cis.service.CisBmDuryroomService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 值班室信息
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmDuryroomService")
@Transactional(readOnly = true)
public class CisBmDuryroomServiceImpl extends
         BaseServiceImpl<CisBmDuryroomDao, CisBmDuryroomInfo, Integer> implements
         CisBmDuryroomService {
	
	@Autowired
	private CisBmDuryroomDao cisBmDuryroomDao;
	
	@Autowired
	public CisBmDuryroomServiceImpl(CisBmDuryroomDao cisBmDuryroomDao) {
		super(cisBmDuryroomDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer duryroomId){
		resetResultMap();
		try{
			CisBmDuryroomInfo bean = cisBmDuryroomDao.findOne(duryroomId);
			bean.setDelSign("N");
			cisBmDuryroomDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> duryroomIds){
		resetResultMap();
		try{
			List<CisBmDuryroomInfo> list = null;
			if(duryroomIds.size()>0){
				List<CisBmDuryroomInfo> listModel = cisBmDuryroomDao.queryListById(duryroomIds);
				for(CisBmDuryroomInfo model : listModel){
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
