package com.nateiot.cis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.nateiot.base.common.EmailUtil;
import com.nateiot.cis.domain.CisEsKnowledge;
import com.nateiot.cis.domain.CisPaPartyRegime;
import com.nateiot.cis.repository.CisEsKnowledgeDao;
import com.nateiot.cis.repository.CisPaPartyRegimeDao;
import com.nateiot.cis.service.CisEsKnowledgeService;
import com.nateiot.cis.service.CisPaPartyRegimeService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 党务建设 -- 党务公开制度
 * 
 * @author Guohw
 *
 */
@Service(value = "CisPaPartyRegimeService")
@Transactional(readOnly = true)
public class CisPaPartyRegimeServiceImpl extends
         BaseServiceImpl<CisPaPartyRegimeDao, CisPaPartyRegime, Integer> implements
         CisPaPartyRegimeService {
	
	@Autowired
	private CisPaPartyRegimeDao cisPaPartyRegimeDao;
	
	@Autowired
	public CisPaPartyRegimeServiceImpl(CisPaPartyRegimeDao cisPaPartyRegimeDao) {
		super(cisPaPartyRegimeDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> regimeIds){
		resetResultMap();
		try{
			List<CisPaPartyRegime> list = null;
			if(regimeIds.size()>0){
				List<CisPaPartyRegime> listModel = cisPaPartyRegimeDao.queryListById(regimeIds);
				for(CisPaPartyRegime model : listModel){
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
	
	@Override
	@Transactional
	public Map<String, Object> doSave(CisPaPartyRegime cisPaPartyRegime) {
		resetResultMap();
		try {
			CisPaPartyRegime entity = cisPaPartyRegimeDao.save(cisPaPartyRegime);
			setResultStatus(0, "保存成功");
			resultMap.put(RESULT_ROW, cisPaPartyRegimeDao.save(entity));
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 根据知识库ID，获取数据
	 * 
	 * @param knowledgeId
	 * @return
	 */
	private CisPaPartyRegime getPartyRegime(Integer regimeId) {
		CisPaPartyRegime entity = cisPaPartyRegimeDao.findOne(regimeId);
		if (entity == null) {
			resultMap.put(RESULT_CODE, 1);
			resultMap.put(RESULT_MSG, "找不到对应的知识库！");
		}
		return entity;
	}

	@Override
	public Map<String, Object> sendmail(String emailAddress, Integer regimeId) {
		resetResultMap();
		try {
			// 获取知识库
			CisPaPartyRegime partyRegime = getPartyRegime(regimeId);
			if (partyRegime == null) {
				return resultMap;
			}
			// 发送邮件
			EmailUtil.sendMail(emailAddress, partyRegime.getRegimeTitle(),
					partyRegime.getRemark());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "发送成功。");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "系统出错。");
		}
		return resultMap;
	}
	
	/**
	 * 手机端发送邮件 
	 */
	@Override
	public Map<String, Object> sendmail2(String emailAddress,
			Integer regimeId) {
		try {
			// 获取知识库
			CisPaPartyRegime partyRegime = getPartyRegime(regimeId);
			if (partyRegime == null) {
				return resultMap;
			}
			// 获取经济户口的邮箱地址
			String emailAddrs = emailAddress;
			if (emailAddrs == null) {
				return resultMap;
			}
			// 发送邮件
			EmailUtil.sendMail(emailAddrs, partyRegime.getRegimeTitle(),
					partyRegime.getRemark());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "发送成功。");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "系统出错。");
		}
		return resultMap;
	}

	/**
	 * 党务建设 -- 工作台 
	 */
	@Override
	public Map<String, Object> getData() {
		resetResultMap();
		try{
			Map<String, Object> partyRegime = cisPaPartyRegimeDao.findTheMaxPartyRegimeId();
			
			resultMap.put(RESULT_ROW, partyRegime.get("remark").toString());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "党务公开制度查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "党务公开制度查询失败");
		}
		return resultMap;
	}

}
