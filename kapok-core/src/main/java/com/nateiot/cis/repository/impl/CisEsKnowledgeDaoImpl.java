package com.nateiot.cis.repository.impl;

import java.util.Map;

import com.nateiot.cis.domain.CisEsKnowledge;
import com.nateiot.cis.repository.CisEsKnowledgeDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 * 考核督办--知识库
 * 
 * @author guohuawen
 *
 */
public class CisEsKnowledgeDaoImpl extends BaseDaoImpl implements
		CisEsKnowledgeDaoPlus {

	@Override
	public Map<String, Object> findTheMaxExamineId() {
		String sqlString = "  SELECT"
				+"      tt.KNOWLEDGE_CODE AS knowledgeCode,"
				+"      tt.KNOWLEDGE_CONTENT AS knowledgeContent,"
				+"      tt.KNOWLEDGE_TYPE AS knowledgeType"
				+"  FROM"
				+"      cis_es_knowledge tt"
				+"  WHERE"
				+"      tt.KNOWLEDGE_ID IN("
				+"          SELECT"
				+"              MAX( kl.KNOWLEDGE_ID ) AS knowledgeId"
				+"          FROM"
				+"              cis_es_knowledge kl"
				+"          WHERE"
				+"              kl.KNOWLEDGE_TYPE = 'examine'"
				+"              AND kl.DEL_SIGN = 'N'"
				+"      )";

		return selectOneBySql(sqlString);
	}

}
