package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmOldPeopleH;
import com.nateiot.cis.domain.CisBmOldPeopleL;
import com.nateiot.cis.repository.CisBmOldPeopleHDao;
import com.nateiot.cis.repository.CisBmOldPeopleLDao;
import com.nateiot.cis.service.CisBmOldPeopleHService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Transactional
@Service(value="cisBmOldPeopleHService")
public class CisBmOldPeopleHServiceImpl extends BaseServiceImpl<CisBmOldPeopleHDao, CisBmOldPeopleH, Integer> implements CisBmOldPeopleHService  {

	@Autowired
	private CisBmOldPeopleHDao cisBmOldPeopleHDao ;
	
	@Autowired
	private CisBmOldPeopleLDao cisBmOldPeopleLDao;
	
	@Autowired
	public CisBmOldPeopleHServiceImpl(CisBmOldPeopleHDao d) {
		super(d);
	}
	
	/*
	 * 查询 存在 老年人 的 人员信息(non-Javadoc)
	 */
	@Override
	public Map<String,Object>  getOldPeopleHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable ){
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmOldPeopleHDao.getOldPeoHouseHolder(conditions, pageable);
			
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
	

	@Override
	@Transactional
	public Map<String, Object> doSave(CisBmOldPeopleH entity) {
		resetResultMap();
		if(entity.getOpId()==null){
			entity = cisBmOldPeopleHDao.save(entity);
			List<CisBmOldPeopleL> list = entity.getCisBmOldPeopleL();
			if(list!=null){
				for(CisBmOldPeopleL c : list){
					c.setCisBmOldPeopleH(entity);
				}
			}
			
		}else{
			CisBmOldPeopleH cisBmOldPeopleH = cisBmOldPeopleHDao.findOne(entity.getOpId());
			List<CisBmOldPeopleL> listOld =cisBmOldPeopleH.getCisBmOldPeopleL();
			List<CisBmOldPeopleL> listNew =entity.getCisBmOldPeopleL();
			if(listOld!=null && listOld.size()>0){
				cisBmOldPeopleLDao.deleteInBatch(listOld);
			}
			if(listNew!=null){
				for(CisBmOldPeopleL a : listNew){
					a.setCisBmOldPeopleH(entity);
				}
			}
			entity = cisBmOldPeopleHDao.saveAndFlush(entity);
		
		}
		
		resultMap.put(ERROR_CODE, 0);
		resultMap.put(RESULT_MSG, "保存成功。");
		resultMap.put(RESULT_ROW,entity);
		
		return resultMap;
	}



	/**
	 * 软删除
	 */
	@Override
	@Transactional
	public Map<String,Object> softDel(int householderId){
		resetResultMap();
		try{
			CisBmOldPeopleH bean = cisBmOldPeopleHDao.findByHouseholderId(householderId);
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
		CisBmOldPeopleH bean =null ;
		try {
			bean =cisBmOldPeopleHDao.findByHouseholderId(householderId);
			if(bean!=null){
				resultMap = super.doDelete(bean.getOpId());
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
	public  CisBmOldPeopleH getCisBmOldPeopleHByHouseholderId(int householderId) {
		return cisBmOldPeopleHDao.findByHouseholderId(householderId);
	}

}
