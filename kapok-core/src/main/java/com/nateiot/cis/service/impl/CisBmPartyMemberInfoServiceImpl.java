package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmPartyMemberInfo;
import com.nateiot.cis.repository.CisBmPartyMemberInfoDao;
import com.nateiot.cis.service.CisBmPartyMemberInfoService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;


@Service(value="cisBmPartyMemberInfoService")
@Transactional
public class CisBmPartyMemberInfoServiceImpl extends BaseServiceImpl<CisBmPartyMemberInfoDao, CisBmPartyMemberInfo, Integer> implements CisBmPartyMemberInfoService {

	@Autowired
	public CisBmPartyMemberInfoServiceImpl(CisBmPartyMemberInfoDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmPartyMemberInfoDao cisBmPartyMemberInfoDao;
	
	@Override
	public Map<String, Object>  search (Map<String, SearchFilter> conditions, Pageable pageable){
		
		resetResultMap();

		try {
			
			Page<Map<String, Object>> page = cisBmPartyMemberInfoDao.search(conditions, pageable);
			
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
	public Map<String, Object> findByHouseholderId(Integer hid){
		
		resetResultMap();
		try {
			
			CisBmPartyMemberInfo model = cisBmPartyMemberInfoDao.findByHouseholderId(hid);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			resultMap.put(RESULT_ROW,model);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询失败。");
		}
		
		return resultMap;
	}
	
	@Override
	@Transactional
	public Map<String, Object> delList(List<Integer> ids) {
		resetResultMap();
		try{
			if(ids.size()>0){
				List< CisBmPartyMemberInfo> list = cisBmPartyMemberInfoDao.queryListById(ids);
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


}
