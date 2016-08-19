package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmGuildOrganInfo;
import com.nateiot.cis.domain.CisBmShop;
import com.nateiot.cis.repository.CisBmGuildOrganInfoDao;
import com.nateiot.cis.service.CisBmGuildOrganInfoService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisBmGuildOrganInfoService")
@Transactional
public class CisBmGuildOrganInfoServiceImpl extends BaseServiceImpl<CisBmGuildOrganInfoDao, CisBmGuildOrganInfo, Integer> 
	implements CisBmGuildOrganInfoService  {

	@Autowired
	public CisBmGuildOrganInfoServiceImpl(CisBmGuildOrganInfoDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmGuildOrganInfoDao cisBmGuildOrganInfoDao;
	
	@Override
	@Transactional
	public Map<String, Object> delList(List<Integer> ids) {

		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmGuildOrganInfo> list = cisBmGuildOrganInfoDao.queryListById(ids);
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
