package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisEsKnowledge;
import com.nateiot.core.repository.BaseDao;

/**
 * 考核督办--知识库
 * 
 * @author guohuawen
 *
 */
public interface CisEsKnowledgeDao extends BaseDao<CisEsKnowledge, Integer>,
		CisEsKnowledgeDaoPlus {

	@Query("select c from CisEsKnowledge c where c.knowledgeId in (:knowledgeIds) " )
	public List<CisEsKnowledge> queryListById(@Param("knowledgeIds")List<Integer> knowledgeIds);
}
