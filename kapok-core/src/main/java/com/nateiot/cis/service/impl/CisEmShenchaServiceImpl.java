package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nateiot.cis.domain.CisEmShencha;
import com.nateiot.cis.repository.CisEmShenchaDao;
import com.nateiot.cis.service.CisEmShenchaService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisEmShenchaService")
public class CisEmShenchaServiceImpl extends BaseServiceImpl<CisEmShenchaDao, CisEmShencha, Integer> implements CisEmShenchaService{

	@Autowired
	private CisEmShenchaDao	cisEmShenchaDao;
	@Autowired
	public CisEmShenchaServiceImpl(CisEmShenchaDao d) {
		super(d);
	}
	
	@Override
	public Map<String, Object> findByYingjiShijianId(Integer yingjiShijianId) {
		try{
		   resetResultMap();
		   List<CisEmShencha> list = cisEmShenchaDao.findByYingjiShijianId(yingjiShijianId);
		   if(! list.isEmpty()){
			   CisEmShencha row = list.get(0);
			   setResultStatus(0, "查询成功！");
			   resultMap.put(RESULT_ROW, row);
		   }
		   setResultStatus(0, "没有匹配数据！");
		   return resultMap;
		}catch(Exception e){
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

}
