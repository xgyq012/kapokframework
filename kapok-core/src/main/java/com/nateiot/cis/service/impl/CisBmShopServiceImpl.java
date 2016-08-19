package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmShop;
import com.nateiot.cis.repository.CisBmShopDao;
import com.nateiot.cis.service.CisBmShopService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 门店信息
 * 
 * @author Administrator
 *
 */
@Service(value = "CisBmShopService")
@Transactional(readOnly = true)
public class CisBmShopServiceImpl extends
         BaseServiceImpl<CisBmShopDao, CisBmShop, Integer> implements
         CisBmShopService {
	
	@Autowired
	private CisBmShopDao cisBmShopDao;
	
	@Autowired
	public CisBmShopServiceImpl(CisBmShopDao cisBmShopDao) {
		super(cisBmShopDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer shopId){
		resetResultMap();
		try{
			CisBmShop bean = cisBmShopDao.findOne(shopId);
			bean.setDelSign("N");
			cisBmShopDao.save(bean);
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
	public Map<String, Object> delList(List<Integer> ids) {

		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmShop> list = cisBmShopDao.queryListById(ids);
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
	
	/**
	 * 门店查询
	 */
	@Override
	public Map<String, Object> queryShop(Map<String, SearchFilter> conditions,
		Pageable pageable) {
		resetResultMap();
		
		try {
			Page<Map<String, Object>> page = cisBmShopDao.queryShop(conditions, pageable);
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
