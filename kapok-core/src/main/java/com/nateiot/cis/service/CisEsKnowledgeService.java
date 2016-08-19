package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisEsKnowledge;
import com.nateiot.core.service.BaseService;
import com.nateiot.cis.repository.CisEsKnowledgeDao;

/**
 * 考核督办--知识库
 * 
 * @author guohuawen
 *
 */
public interface CisEsKnowledgeService extends
	BaseService<CisEsKnowledgeDao, CisEsKnowledge, Integer> {
	
	public Map<String, Object> softDelList(List<Integer> knowledgeIds);
	
	public Map<String, Object> doSave(CisEsKnowledge cisEsKnowledge);
	
	public Map<String, Object> sendmail(String emailAddress, Integer knowledgeId);
	
	public Map<String, Object> sendmail2(String emailAddress, Integer knowledgeId);
	
	public Map<String, Object> getData();
}
