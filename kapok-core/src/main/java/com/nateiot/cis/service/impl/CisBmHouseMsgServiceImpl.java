package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.cis.domain.CisBmHouseMsg;
import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.cis.repository.CisBmHouseMsgDao;
import com.nateiot.cis.service.CisBmHouseMsgService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisBmHouseMsgService")
@Transactional
public class CisBmHouseMsgServiceImpl  extends BaseServiceImpl<CisBmHouseMsgDao,CisBmHouseMsg, Integer> implements CisBmHouseMsgService {

	@Autowired
	public CisBmHouseMsgServiceImpl(CisBmHouseMsgDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmHouseMsgDao cisBmHouseMsgDao;
	
	
	public Map<String,Object>  selectHouse(Map<String, SearchFilter> conditions, Pageable pageable){
		
		resetResultMap();
		
		try {
			
			Page<Map<String, Object>> page = cisBmHouseMsgDao.selectHouse(conditions, pageable);
			
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
	public Map<String, Object> delList(List<Integer> ids) {
	 
		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmHouseMsg> list = cisBmHouseMsgDao.queryListById(ids);
				resultMap = super.doDelete(list);
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
	public Map<String, Object> softDel(List<Integer> ids) {
		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmHouseMsg> list = cisBmHouseMsgDao.queryListById(ids);
				for(CisBmHouseMsg model : list ){
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
	public Map<String, Object> softDel(Integer id) {
		resetResultMap();
		try{
			boolean boo = isExistHouseForHolder(id);
			if(!boo){
				CisBmHouseMsg bean = cisBmHouseMsgDao.findOne(id);
				bean.setDelSign("Y");
				resultMap.put(RESULT_CODE, 0);
				resultMap.put(RESULT_MSG, "删除成功");
			}else{
				resultMap.put(RESULT_CODE,2);
				resultMap.put(RESULT_MSG, "无法删除");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> existsHouseByBuildId(Integer buildId) {
		resetResultMap();
		try{
			boolean  boo = false ;
			List<CisBmHouseMsg> list = cisBmHouseMsgDao.queryListByBuildId(buildId);
			if(list!=null && list.size()>0){
				boo = true ; 
			}
			resultMap.put("isExists", boo ) ;
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	
	public  boolean isExistHouseForHolder(Integer houseId){
		boolean boo = false ;
		List<Map<String,Object>> list = cisBmHouseMsgDao.getExistsHouseForHolder(houseId);
		if(list!=null && list.size()>0){
			Map<String,Object> map = list.get(0);
			boo = Integer.parseInt(map.get("counts").toString()) > 0;
		}
		return boo ;
	}

	@Override
	@Transactional
	public Map<String, Object> updateHouseLonAndLat(Integer houseId, String lon, String lat) {
		resetResultMap();
		try{
			CisBmHouseMsg model = cisBmHouseMsgDao.findOne(houseId);
			model.setLat(lat);
			model.setLon(lon);
			resultMap.put(RESULT_CODE,0);
			resultMap.put(RESULT_MSG, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错");
		}
		return resultMap;
	}
	
	
	/**
	 * 查询房屋信息，可根据人口信息中姓名来查询所属房屋信息
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String,Object>  queryHouseByHolder(Map<String, SearchFilter> conditions, Pageable pageable){
		
			resetResultMap();
			
			try {
				Page<Map<String, Object>> page = cisBmHouseMsgDao.queryHouseByHolder(conditions, pageable);
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

	/**
	 * 查询房屋信息，可根据人口信息中姓名来查询所属房屋信息
	 * @param pageable
	 * @param args
	 * @return
	 */
	public Map<String,Object>  queryHouseByHolderLikeArgs(Pageable pageable,String args,String meshId){

			resetResultMap();

			try {
				Page<Map<String, Object>> page = cisBmHouseMsgDao.queryHouseByHolderLikeArgs(null,pageable,args,meshId);
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
