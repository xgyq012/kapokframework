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
import com.nateiot.cis.repository.CisEsKnowledgeDao;
import com.nateiot.cis.service.CisEsKnowledgeService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 考核督办--知识库
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisEsKnowledgeService")
@Transactional(readOnly = true)
public class CisEsKnowledgeServiceImpl extends
         BaseServiceImpl<CisEsKnowledgeDao, CisEsKnowledge, Integer> implements
         CisEsKnowledgeService {
	
	@Autowired
	private CisEsKnowledgeDao cisEsKnowledgeDao;
	
	@Autowired
	public CisEsKnowledgeServiceImpl(CisEsKnowledgeDao cisEsKnowledgeDao) {
		super(cisEsKnowledgeDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> knowledgeIds){
		resetResultMap();
		try{
			List<CisEsKnowledge> list = null;
			if(knowledgeIds.size()>0){
				List<CisEsKnowledge> listModel = cisEsKnowledgeDao.queryListById(knowledgeIds);
				for(CisEsKnowledge model : listModel){
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
	public Map<String, Object> doSave(CisEsKnowledge cisEsKnowledge) {
		resetResultMap();
//		maintainDefaultValue(CisEsKnowledge);
		try {
			CisEsKnowledge entity = cisEsKnowledgeDao.save(cisEsKnowledge);
//			Integer knowledgeDocId = cisEsKnowledge.getKnowledgeDocId();
//			if(knowledgeDocId != null){
//				GxwlSysDoc gxwlSysDoc = gxwlSysDocDao.findOne(knowledgeDocId);
//				gxwlSysDoc.setIsTemp("N");
//			}
			setResultStatus(0, "保存成功");
			resultMap.put(RESULT_ROW, cisEsKnowledgeDao.save(cisEsKnowledge));
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
	private CisEsKnowledge getKnowledge(Integer knowledgeId) {
		CisEsKnowledge knowledge = cisEsKnowledgeDao.findOne(knowledgeId);
		if (knowledge == null) {
			resultMap.put(RESULT_CODE, 1);
			resultMap.put(RESULT_MSG, "找不到对应的知识库！");
		}
		return knowledge;
	}

	/**
	 * 获取邮箱地址
	 * 
	 * @param enterpriseIds
	 * @return
	 */
//	private String getEmailAddrs(String enterpriseIds) {
//		StringBuilder emailAddrs = new StringBuilder();
//		List<Integer> idList = new ArrayList<Integer>();
//		String[] ids = enterpriseIds.split(",");
//		for (String id : ids) {
//			idList.add(Integer.parseInt(id));
//		}
//		List<SisEnEntadd> entadds = sisEnEntaddDao.queryByEnterpriseIds(idList);
//		for (SisEnEntadd e : entadds) {
//			if (!StringUtils.isEmpty(e.getRealEmail())) {
//				emailAddrs.append(e.getRealEmail() + " ");
//			}
//		}
//		if (emailAddrs.toString().length() == 0) {
//			resultMap.put(RESULT_CODE, 1);
//			resultMap.put(RESULT_MSG, "找不到经济户口的邮箱地址。");
//			return null;
//		} else {
//			return emailAddrs.toString().substring(0,
//					emailAddrs.toString().length() - 1);
//		}
//	}

	@Override
	public Map<String, Object> sendmail(String emailAddress, Integer knowledgeId) {
		resetResultMap();
		try {
			// 获取知识库
			CisEsKnowledge knowledge = getKnowledge(knowledgeId);
			if (knowledge == null) {
				return resultMap;
			}
			// 发送邮件
			EmailUtil.sendMail(emailAddress, knowledge.getKnowledgeTitle(),
					knowledge.getKnowledgeContent());
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
			Integer knowledgeId) {
		try {
			// 获取知识库
			CisEsKnowledge knowledge = getKnowledge(knowledgeId);
			if (knowledge == null) {
				return resultMap;
			}
			// 获取经济户口的邮箱地址
			String emailAddrs = emailAddress;
			if (emailAddrs == null) {
				return resultMap;
			}
			// 发送邮件
			EmailUtil.sendMail(emailAddrs, knowledge.getKnowledgeTitle(),
					knowledge.getKnowledgeContent());
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
	 * 考核督办工作台  
	 */
	@Override
	public Map<String, Object> getData() {
		resetResultMap();
		try{
			Map<String, Object> kl = cisEsKnowledgeDao.findTheMaxExamineId();
			resultMap.put(RESULT_ROW, kl.get("knowledgeContent").toString());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "获取数据成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "获取数据出错");
		}
		return resultMap;
	}
	
}
