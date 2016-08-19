package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmShop;
import com.nateiot.cis.domain.CisBmWomanMsg;
import com.nateiot.cis.repository.CisBmWomanMsgDao;
import com.nateiot.cis.service.CisBmWomanMsgService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisBmWomanMsgService")
@Transactional
public class CisBmWomanMsgServiceImpl extends BaseServiceImpl<CisBmWomanMsgDao, CisBmWomanMsg, Integer> implements CisBmWomanMsgService {

	@Autowired
	public CisBmWomanMsgServiceImpl(CisBmWomanMsgDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmWomanMsgDao cisBmWomanMsgDao;
	
	
	@Override
	@Transactional
	public Map<String, Object> delList(List<Integer> ids) {

		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmWomanMsg> list = cisBmWomanMsgDao.queryListById(ids);
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
