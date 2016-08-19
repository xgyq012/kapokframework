package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.cis.domain.CisBmInspectMsg;
import com.nateiot.cis.repository.CisBmInspectMsgDao;
import com.nateiot.cis.service.CisBmInspectMsgService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisBmInspectMsgService")
@Transactional
public class CisBmInspectMsgServiceImpl  extends BaseServiceImpl<CisBmInspectMsgDao,CisBmInspectMsg, Integer> implements CisBmInspectMsgService {   
	
	@Autowired
	public CisBmInspectMsgServiceImpl(CisBmInspectMsgDao d) {
		super(d);
	}

	@Autowired
	private CisBmInspectMsgDao cisBmInspectMsgDao;
	
	@Override
	@Transactional
	public Map<String, Object> delList(List<Integer> ids) {
		resetResultMap();
		try{
			if(ids.size()>0){
				List< CisBmInspectMsg> list = cisBmInspectMsgDao.queryListById(ids);
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

