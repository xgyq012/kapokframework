package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmPublicSecurity;
import com.nateiot.cis.repository.CisBmPublicSecurityDao;
import com.nateiot.cis.service.CisBmPublicSecurityService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 治安信息
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisBmPublicSecurityService")
@Transactional(readOnly = true)
public class CisBmPublicSecurityServiceImpl extends
         BaseServiceImpl<CisBmPublicSecurityDao, CisBmPublicSecurity, Integer> implements
         CisBmPublicSecurityService {
	
	@Autowired
	private CisBmPublicSecurityDao cisBmPublicSecurityDao;
	
	@Autowired
	public CisBmPublicSecurityServiceImpl(CisBmPublicSecurityDao cisBmPublicSecurityDao) {
		super(cisBmPublicSecurityDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer puSecurityId){
		resetResultMap();
		try{
			CisBmPublicSecurity bean = cisBmPublicSecurityDao.findOne(puSecurityId);
			bean.setDelSign("N");
			cisBmPublicSecurityDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> puSecurityIds){
		resetResultMap();
		try{
			List<CisBmPublicSecurity> list = null;
			if(puSecurityIds.size()>0){
				List<CisBmPublicSecurity> listModel = cisBmPublicSecurityDao.queryListById(puSecurityIds);
				for(CisBmPublicSecurity model : listModel){
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
