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
import com.nateiot.cis.repository.CisBmBuildingMsgDao;
import com.nateiot.cis.repository.CisBmHouseMsgDao;
import com.nateiot.cis.service.CisBmBuildingMsgService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisBmBuildingMsgService")
@Transactional
public class CisBmBuildingMsgServiceImpl extends BaseServiceImpl< CisBmBuildingMsgDao,CisBmBuildingMsg, Integer> 
	implements CisBmBuildingMsgService,BaseService< CisBmBuildingMsgDao,  CisBmBuildingMsg, Integer> {

	@Autowired
	public CisBmBuildingMsgServiceImpl(CisBmBuildingMsgDao d) {
		super(d);
	}

	@Autowired
	private CisBmBuildingMsgDao cisBmBuildingMsgDao;
	
	@Autowired
	private CisBmHouseMsgDao cisBmHouseMsgDao;

	@Override
	public Map<String, Object> search(Map<String, SearchFilter> conditions, Pageable pageable) {
		
		resetResultMap();
		
		try {
			
			Page<Map<String, Object>> page = cisBmBuildingMsgDao.search(conditions, pageable);
			
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
	public Map<String, Object> softDel(List<Integer> ids){
		resetResultMap();
		try{
			if(ids.size()>0){
				List< CisBmBuildingMsg> list = cisBmBuildingMsgDao.queryListById(ids);
				for( CisBmBuildingMsg model : list ){
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
	public Map<String, Object> delList(List<Integer> ids) {
		resetResultMap();
		try{
			if(ids.size()>0){
				List< CisBmBuildingMsg> list = cisBmBuildingMsgDao.queryListById(ids);
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
	@Transactional
	public Map<String, Object> softDel(Integer id) {
		resetResultMap();
		try{
			boolean boo = false ; 
			List<CisBmHouseMsg> list = cisBmHouseMsgDao.queryListByBuildId(id);
			if(list!=null && list.size()>0){
				boo = true ;
			}
			
			if(!boo){
				CisBmBuildingMsg bean = cisBmBuildingMsgDao.findOne(id);
				bean.setDelSign("Y");
				resultMap.put(RESULT_CODE, 0);
				resultMap.put(RESULT_MSG, "删除成功");
			}else{
				resultMap.put(RESULT_CODE, -2);
				resultMap.put(RESULT_MSG, "删除失败，楼栋信息下存在房屋信息，因此无法删除！");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}

	
}
