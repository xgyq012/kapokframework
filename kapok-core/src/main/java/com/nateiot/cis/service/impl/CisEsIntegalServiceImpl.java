package com.nateiot.cis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEsIntegal;
import com.nateiot.cis.repository.CisEsIntegalDao;
import com.nateiot.cis.service.CisEsIntegalService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 考核督办 -- 积分管理
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisEsIntegalService")
@Transactional(readOnly = true)
public class CisEsIntegalServiceImpl extends
         BaseServiceImpl<CisEsIntegalDao, CisEsIntegal, Integer> implements
         CisEsIntegalService {
	
	@Autowired
	private CisEsIntegalDao cisEsIntegalDao;
	
	@Autowired
	public CisEsIntegalServiceImpl(CisEsIntegalDao cisEsIntegalDao) {
		super(cisEsIntegalDao);
	}
	
	@Override
	public Map<String, Object> softDel(Integer integalId){
		resetResultMap();
		try{
			CisEsIntegal bean = cisEsIntegalDao.findOne(integalId);
			bean.setDelSign("N");
			cisEsIntegalDao.save(bean);
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
	public Map<String, Object> softDelList(List<Integer> saCampusIds){
		resetResultMap();
		try{
			List<CisEsIntegal> list = null;
			if(saCampusIds.size()>0){
				List<CisEsIntegal> listModel = cisEsIntegalDao.queryListById(saCampusIds);
				for(CisEsIntegal model : listModel){
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
	
	/**
	 * 通过积分配置编码，查找分值
	 * 
	 *  @param integalCode
	 *  @return
	 */
	@Override
	public Integer findScore(String integalCode){
		resetResultMap();
		try{
			Map<String, Object> map = cisEsIntegalDao.findScoreByIntegalCode(integalCode);
			Integer score = map.get("score") == null ?  null : Integer.parseInt(map.get("score").toString());
			
//			resultMap.put(RESULT_ROW, score);
//			resultMap.put(RESULT_CODE, 0);
//			resultMap.put(RESULT_MSG, "获取积分分值成功");
			return score == null ? null : score;
		}catch(Exception e){
			e.printStackTrace();
//			resultMap.put(RESULT_CODE, -1);
//			resultMap.put(RESULT_MSG, "获取积分分值出错");
			return null;
		}
	}

	
}
