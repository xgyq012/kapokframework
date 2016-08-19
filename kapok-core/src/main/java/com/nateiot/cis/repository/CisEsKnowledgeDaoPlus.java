package com.nateiot.cis.repository;

import java.util.Map;

import com.nateiot.cis.domain.CisEsKnowledge;

/**
 * 考核督办--知识库
 * 
 * @author guohuawen
 *
 */
public interface CisEsKnowledgeDaoPlus {
	
	public Map<String, Object> findTheMaxExamineId();

}
