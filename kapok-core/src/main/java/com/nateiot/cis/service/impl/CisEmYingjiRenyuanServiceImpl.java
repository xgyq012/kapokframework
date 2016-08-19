package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEmEmePlace;
import com.nateiot.cis.domain.CisEmYingjiRenyuan;
import com.nateiot.cis.repository.CisEmYingjiRenyuanDao;
import com.nateiot.cis.service.CisEmYingjiRenyuanService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 应急人员
 * @author xiewenhua
 *
 */
@Service(value = "CisEmYingjiRenyuanService")
@Transactional
public class CisEmYingjiRenyuanServiceImpl extends BaseServiceImpl<CisEmYingjiRenyuanDao, CisEmYingjiRenyuan, Integer> implements CisEmYingjiRenyuanService {
	@Autowired
	private CisEmYingjiRenyuanDao cisEmYingjiRenyuanDao;
	
	@Autowired
	public CisEmYingjiRenyuanServiceImpl(CisEmYingjiRenyuanDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> ids) {
		resetResultMap();
		try{
			List<CisEmYingjiRenyuan> list = null;
			if(ids.size() > 0){
				List<CisEmYingjiRenyuan> listModel = cisEmYingjiRenyuanDao.queryListById(ids);
				for(CisEmYingjiRenyuan model : listModel){
					model.setDelSign("Y");
				}
				list = listModel;
				resultMap = super.doSave(list);
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}


}
