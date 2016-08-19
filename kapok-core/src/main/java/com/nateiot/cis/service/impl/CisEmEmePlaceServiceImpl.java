package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEmEmePlace;
import com.nateiot.cis.repository.CisEmEmePlaceDao;
import com.nateiot.cis.service.CisEmEmePlaceService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 应急管理 -- 避难场所
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisEmEmePlaceService")
@Transactional(readOnly = true)
public class CisEmEmePlaceServiceImpl extends
         BaseServiceImpl<CisEmEmePlaceDao, CisEmEmePlace, Integer> implements
         CisEmEmePlaceService {
	
	@Autowired
	private CisEmEmePlaceDao cisEmEmePlaceDao;
	
	@Autowired
	public CisEmEmePlaceServiceImpl(CisEmEmePlaceDao cisEmEmePlaceDao) {
		super(cisEmEmePlaceDao);
	}
	
//	@Override
//	public Map<String, Object> softDel(Integer saCampusId){
//		resetResultMap();
//		try{
//			CisEmEmePlace bean = cisEmEmePlaceDao.findOne(saCampusId);
//			bean.setDelSign("N");
//			cisEmEmePlaceDao.save(bean);
//			resultMap.put(RESULT_CODE, 0);
//			resultMap.put(RESULT_MSG, "删除成功");
//		}catch(Exception e){
//			e.printStackTrace();
//			resultMap.put(RESULT_CODE, -1);
//			resultMap.put(RESULT_MSG, "删除出错");
//		}
//		
//		return resultMap;
//	}
//	
	@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> emePlaceIds){
		resetResultMap();
		try{
			List<CisEmEmePlace> list = null;
			if(emePlaceIds.size()>0){
				List<CisEmEmePlace> listModel = cisEmEmePlaceDao.queryListById(emePlaceIds);
				for(CisEmEmePlace model : listModel){
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

	@Override
	public Map<String, Object> queryRefuge(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		
		try {
			Page<Map<String, Object>> page = cisEmEmePlaceDao.queryRefuge(conditions, pageable);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询失败。");
		}
	
		return resultMap;
	}
	
}
