package com.nateiot.cis.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.common.BaseUtil;
import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisBmFireControl;
import com.nateiot.cis.repository.CisBmFireControlDao;
import com.nateiot.cis.service.CisBmFireControlService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 消防信息
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisBmFireControlService")
@Transactional(readOnly = true)
public class CisBmFireControlServiceImpl extends
         BaseServiceImpl<CisBmFireControlDao, CisBmFireControl, Integer> implements
         CisBmFireControlService {
	
	@Autowired
	private CisBmFireControlDao cisBmFireControlDao;
	
	@Autowired
	private GxwlSysDocService gxwlSysDocService;
	
	@Autowired
	private GxwlSysDocDao gxwlSysDocDao;
	
	@Autowired
	public CisBmFireControlServiceImpl(CisBmFireControlDao cisBmFireControlDao) {
		super(cisBmFireControlDao);
	}
	
	
	
	@Override
	@Transactional
	public Map<String, Object> doSave(CisBmFireControl entity) {
		 if(entity.getPictureId()!=null){
			 gxwlSysDocService.moveDoc(entity.getPictureId());
		 }
		return super.doSave(entity);
	}
	

	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer id) {
		CisBmFireControl model = cisBmFireControlDao.findOne(id);
		if(model!=null && model.getPictureId()!=null){
			gxwlSysDocService.deleteFile(model.getPictureId());
		}
		return super.doDelete(id);
	}


	@Override
	@Transactional
	public Map<String, Object> softDel(Integer fireControlId){
		resetResultMap();
		try{
			CisBmFireControl bean = cisBmFireControlDao.findOne(fireControlId);
			bean.setDelSign("N");
			cisBmFireControlDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> fireControlIds){
		resetResultMap();
		try{
			List<CisBmFireControl> list = null;
			if(fireControlIds.size()>0){
				List<CisBmFireControl> listModel = cisBmFireControlDao.queryListById(fireControlIds);
				for(CisBmFireControl model : listModel){
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
	public Map<String, Object> count(String meshIds) {
		return cisBmFireControlDao.count(meshIds);
	}
	
}
