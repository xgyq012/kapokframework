package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmSocialSecurity;
import com.nateiot.cis.repository.CisBmSocialSecurityDao;
import com.nateiot.cis.service.CisBmSocialSecurityService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 社保信息
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmSocialSecurityService")
@Transactional(readOnly = true)
public class CisBmSocialSecurityServiceImpl extends
         BaseServiceImpl<CisBmSocialSecurityDao, CisBmSocialSecurity, Integer> implements
         CisBmSocialSecurityService {
	
	@Autowired
	private CisBmSocialSecurityDao cisBmSocialSecurityDao;
	
	@Autowired
	public CisBmSocialSecurityServiceImpl(CisBmSocialSecurityDao cisBmSocialSecurityDao) {
		super(cisBmSocialSecurityDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer socialSecurityId){
		resetResultMap();
		try{
			CisBmSocialSecurity bean = cisBmSocialSecurityDao.findOne(socialSecurityId);
			bean.setDelSign("N");
			cisBmSocialSecurityDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> socialSecurityIds){
		resetResultMap();
		try{
			List<CisBmSocialSecurity> list = null;
			if(socialSecurityIds.size()>0){
				List<CisBmSocialSecurity> listModel = cisBmSocialSecurityDao.queryListById(socialSecurityIds);
				for(CisBmSocialSecurity model : listModel){
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
