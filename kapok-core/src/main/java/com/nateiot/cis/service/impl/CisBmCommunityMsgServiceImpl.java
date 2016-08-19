package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisBmCommunityMsg;
import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.cis.repository.CisBmCommunityMsgDao;
import com.nateiot.cis.service.CisBmCommunityMsgService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value="cisBmCommunityMsgService")
@Transactional
public class CisBmCommunityMsgServiceImpl extends BaseServiceImpl<CisBmCommunityMsgDao, 
	CisBmCommunityMsg, Integer> implements CisBmCommunityMsgService {

	@Autowired
	public CisBmCommunityMsgServiceImpl(CisBmCommunityMsgDao d) {
		super(d);
	}
	
	@Autowired
	private CisBmCommunityMsgDao cisBmCommunityMsgDao;

	@Override
	@Transactional
	public Map<String, Object> softDel(List<Integer> ids){
		resetResultMap();
		try{
			if(ids.size()>0){
				List<CisBmCommunityMsg> list = cisBmCommunityMsgDao.queryListById(ids);
				for(CisBmCommunityMsg model : list ){
					model.setDelSign("Y");
				}
				resultMap = super.doSave(list);
				resultMap.put(RESULT_MSG, "删除成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> softDel(Integer id) {
		resetResultMap();
		try{
			boolean boo = this.isDelCommunity(id);
			if(!boo){
				CisBmCommunityMsg model = cisBmCommunityMsgDao.findOne(id);
				model.setDelSign("Y");
				resultMap = super.doSave(model);
				resultMap.put(RESULT_MSG, "删除成功");
			}else{
				resultMap.put(RESULT_CODE,2);
				resultMap.put(RESULT_MSG, "无法删除");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}
	
	/**
	 * 是否能删除小区信息
	 * @param id
	 * @return
	 */
	public boolean isDelCommunity(Integer id){
		boolean boo = false ;
		List<Map<String,Object>> list = cisBmCommunityMsgDao.getExistsCommunityForBuildORHouse(id);
		if(list!=null && list.size()>0){
			Map<String,Object> map = list.get(0);
			boo = Integer.parseInt(map.get("counts").toString()) > 0;
		}
		return boo ;
	}

}
