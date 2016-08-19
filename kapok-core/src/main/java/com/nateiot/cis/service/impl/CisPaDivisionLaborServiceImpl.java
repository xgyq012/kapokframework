package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisPaDivisionLabor;
import com.nateiot.cis.domain.CisPaDivisionRowtable;
import com.nateiot.cis.repository.CisPaDivisionLaborDao;
import com.nateiot.cis.repository.CisPaDivisionRowtableDao;
import com.nateiot.cis.service.CisPaDivisionLaborService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 党务建设 -- 两委分工
 * 
 *  @author Guohw
 */
@Service(value = "CisPaDivisionLaborService")
@Transactional(readOnly=true)
public class CisPaDivisionLaborServiceImpl 
		extends BaseServiceImpl<CisPaDivisionLaborDao, CisPaDivisionLabor, Integer>
		implements CisPaDivisionLaborService{
	
	@Autowired
	private CisPaDivisionLaborDao cisPaDivisionLaborDao;
	
	@Autowired
	private CisPaDivisionRowtableDao cisPaDivisionRowtableDao;
	
	@Autowired
	public CisPaDivisionLaborServiceImpl(CisPaDivisionLaborDao d){
		super(d);
	}
	
	/**
	 * 软删除 
	 */
	@Override
	@Transactional
	public Map<String, Object> softDel(Integer laborId) {
		resetResultMap();
		try{
			CisPaDivisionLabor entity = cisPaDivisionLaborDao.findOne(laborId);
			entity.setDelSign("Y");
			
			resultMap.put(RESULT_ROW, cisPaDivisionLaborDao.save(entity));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}

	/**
	 * 保存 
	 */
	@Override
	@Transactional
	public Map<String, Object> saveEn(CisPaDivisionLabor entity) {
		resetResultMap();
		try{
			Integer laborId = entity.getLaborId();
			
			if(laborId == null){
				CisPaDivisionLabor bean = cisPaDivisionLaborDao.save(entity);
				List<CisPaDivisionRowtable> rowtable = entity.getCisPaDivisionRowtable();
				if(rowtable != null){
					for(CisPaDivisionRowtable row : rowtable){
						row.setLaborId(bean.getLaborId());
					}
				}
			}else{
				CisPaDivisionLabor bean = cisPaDivisionLaborDao.findOne(laborId);
				
				List<CisPaDivisionRowtable> news = entity.getCisPaDivisionRowtable();
				List<CisPaDivisionRowtable> olds = bean.getCisPaDivisionRowtable();
				
				if(news == null){
					cisPaDivisionRowtableDao.deleteInBatch(olds);
				}else{
					for(CisPaDivisionRowtable oldsRow : olds){
						if(!news.contains(olds)){
							cisPaDivisionRowtableDao.delete(oldsRow);
						}
					}
					for(CisPaDivisionRowtable drt : news){
						drt.setLaborId(bean.getLaborId());
					}
				}
			}
			
			resultMap.put(RESULT_ROW, cisPaDivisionLaborDao.save(entity));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错");
		}
		return resultMap;
	}
	
	/**
	 * 查找党员 
	 */
	@Override
	public Map<String, Object> findMember(Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisPaDivisionLaborDao.findMember(conditions, pageable);
			
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		return resultMap;
	}

}
