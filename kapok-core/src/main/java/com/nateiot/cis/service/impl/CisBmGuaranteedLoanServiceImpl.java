package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmGuaranteedLoan;
import com.nateiot.cis.repository.CisBmGuaranteedLoanDao;
import com.nateiot.cis.service.CisBmGuaranteedLoanService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 小额担保贷款
 * 
 * @author guohuawen
 *
 */
@Service(value = "CisBmGuaranteedLoanService")
@Transactional(readOnly = true)
public class CisBmGuaranteedLoanServiceImpl extends
         BaseServiceImpl<CisBmGuaranteedLoanDao, CisBmGuaranteedLoan, Integer> implements
         CisBmGuaranteedLoanService {
	
	@Autowired
	private CisBmGuaranteedLoanDao cisBmGuaranteedLoanDao;
	
	@Autowired
	public CisBmGuaranteedLoanServiceImpl(CisBmGuaranteedLoanDao cisBmGuaranteedLoanDao) {
		super(cisBmGuaranteedLoanDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer guaranteedId){
		resetResultMap();
		try{
			//CisBmGuaranteedLoan bean = cisBmGuaranteedLoanDao.findOne(guaranteedLoanId);
			//bean.setDelSign("N");
			//cisBmGuaranteedLoanDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> guaranteedIds){
		resetResultMap();
		try{
			List<CisBmGuaranteedLoan> list = null;
			if(guaranteedIds.size()>0){
				//List<CisBmGuaranteedLoan> listModel = cisBmGuaranteedLoanDao.queryListById(guaranteedLoanIds);
				//for(CisBmGuaranteedLoan model : listModel){
					//model.setDelSign("Y");
				//}
				//list = listModel;
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
