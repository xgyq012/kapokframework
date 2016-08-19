package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEsSupervision;
import com.nateiot.cis.repository.CisEsSupervisionDao;
import com.nateiot.cis.service.CisEsSupervisionService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 考核督办 -- 事件督办
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisEsSupervisionService")
@Transactional(readOnly = true)
public class CisEsSupervisionServiceImpl extends
         BaseServiceImpl<CisEsSupervisionDao, CisEsSupervision, Integer> implements
         CisEsSupervisionService {
	
	@Autowired
	private CisEsSupervisionDao cisEsSupervisionDao;
	
	@Autowired
	public CisEsSupervisionServiceImpl(CisEsSupervisionDao cisEsSupervisionDao) {
		super(cisEsSupervisionDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer saCampusId){
		resetResultMap();
		try{
			CisEsSupervision bean = cisEsSupervisionDao.findOne(saCampusId);
			bean.setDelSign("N");
			cisEsSupervisionDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> saCampusIds){
		resetResultMap();
		try{
			List<CisEsSupervision> list = null;
			if(saCampusIds.size()>0){
				List<CisEsSupervision> listModel = cisEsSupervisionDao.queryListById(saCampusIds);
				for(CisEsSupervision model : listModel){
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
