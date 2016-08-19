package com.nateiot.cis.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisBmHolderRelationship;
import com.nateiot.cis.repository.CisBmHolderRelationshipDao;
import javassist.expr.Cast;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.cis.repository.CisBmHouseholderDao;
import com.nateiot.cis.service.CisBmHouseholderService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmHouseholderService")
@Transactional
public class CisBmHouseholderServiceImpl extends BaseServiceImpl<CisBmHouseholderDao, CisBmHouseholder, Integer> implements
CisBmHouseholderService,BaseService<CisBmHouseholderDao, CisBmHouseholder, Integer> {
	
	@Autowired
	private CisBmHouseholderDao cisBmHouseholderDao;
	
	@Autowired
	private GxwlSysDocService gxwlSysDocService;

	@Autowired
	private CisBmHolderRelationshipDao cisBmHolderRelationshipDao;
	
	@Autowired
	public CisBmHouseholderServiceImpl(CisBmHouseholderDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> softDel(Integer id){
		resetResultMap();
		try{
			CisBmHouseholder bean = cisBmHouseholderDao.findOne(id);
			bean.setDelSign("Y");
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
	public Map<String, Object> softDel(List<Integer> ids){
		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmHouseholder> list = cisBmHouseholderDao.queryListById(ids);
				for(CisBmHouseholder model : list ){
					model.setDelSign("Y");
				}
				resultMap = super.doSave(list);
				resultMap.put(RESULT_MSG, "删除成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		
		return resultMap;
	}

	
	@Override
	@Transactional
	public Map<String, Object> doSave(CisBmHouseholder entity) {

		Map<String,Object> resultMap = new HashedMap();

		try{

			if(entity.getPhotofileID()!=null){
				gxwlSysDocService.moveDoc(entity.getPhotofileID());
			}

			if(entity.getHzId()!=null){

				if(entity.getHouseholderId()==null){//新增

					//插入关系
					CisBmHolderRelationship rp = new CisBmHolderRelationship();
					rp.setHolderRelationship(entity.getFamilytree());
					rp.setFollowId(entity.getHzId());
					rp = cisBmHolderRelationshipDao.save(rp);

					entity = cisBmHouseholderDao.save(entity);
					rp.setHouseholderId(entity.getHouseholderId());

					resultMap.put(RESULT_CODE,0);
					resultMap.put(RESULT_ROW,entity);
					resultMap.put(RESULT_MSG,"保存成功");

				}else{

					//修改
					CisBmHolderRelationship rp = cisBmHolderRelationshipDao.getCisBmHolderRelationshipByHouseHolderId(entity.getHouseholderId());
					if(rp==null){
						rp = new CisBmHolderRelationship();
						rp.setHolderRelationship(entity.getFamilytree());
						rp.setFollowId(entity.getHzId());
						rp.setHouseholderId(entity.getHouseholderId());
						rp = cisBmHolderRelationshipDao.save(rp);

					}else{
						rp.setFollowId(entity.getHzId());
						rp.setHolderRelationship(entity.getFamilytree());
						rp = cisBmHolderRelationshipDao.save(rp);
					}

					entity = cisBmHouseholderDao.save(entity);
					resultMap.put(RESULT_CODE,0);
					resultMap.put(RESULT_ROW,entity);
					resultMap.put(RESULT_MSG,"保存成功");

					return  resultMap;
				}

			}else{
				//户主ID为空的时候
				CisBmHolderRelationship rp = cisBmHolderRelationshipDao.getCisBmHolderRelationshipByHouseHolderId(entity.getHouseholderId());
				if(rp!=null){
					cisBmHolderRelationshipDao.delete(rp);
				}

				return super.doSave(entity);

			}

		}catch (Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE,-1);
			resultMap.put(RESULT_ROW,entity);
			resultMap.put(RESULT_MSG,"保存失败");
		}
		 
		return resultMap;
	}

	@Override
	public Map<String, Object> doSelect(Integer id) {

		Map<String,Object> resultMap = new HashMap<String,Object>();

		try {

			CisBmHouseholder model = cisBmHouseholderDao.findOne(id);
			CisBmHolderRelationship rp = cisBmHolderRelationshipDao.getCisBmHolderRelationshipByHouseHolderId(model.getHouseholderId());
			if(rp!=null){
				model.setHzName(cisBmHouseholderDao.findOne(rp.getFollowId()).getHouseholderName());
				model.setHzId(rp.getFollowId());
				model.setFamilytree(rp.getHolderRelationship());
			}
			resultMap.put(RESULT_CODE,0);
			resultMap.put(RESULT_ROW,model);
			resultMap.put(RESULT_MSG,"查询成功");

		}catch (Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE,-1);
			resultMap.put(RESULT_MSG,"查询失败！");
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> getNotHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try {
			
			Page<Map<String, Object>> page = cisBmHouseholderDao.getNotHouseHolder(conditions, pageable);
			
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
	public Map<String, Object> getCountResult(String meshIds) {
		return cisBmHouseholderDao.getCountResult(meshIds);
	}

	 

}
