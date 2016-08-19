package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.repository.CisEsModiaryDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 * 考核督办 -- 民情日记统计
 * 
 * @author Administrator
 *
 */
public class CisEsModiaryDaoImpl extends BaseDaoImpl implements
		CisEsModiaryDaoPlus {
	
	@Override
	public List<Map<String, Object>> moDiaryFormSearch(
			Map<String, SearchFilter> conditions){
		String sqlString = ""
						 + " SELECT"
						 + "  modiaryId,"
						 + "  modiaryCode,"
						 + "  modiaryUnit,"
						 + "  modiaryName,"
						 + "  actor,"
						 + "  fine,"
						 + "  middle,"
						 + "  bad,"
						 + "  total"
						 + " FROM"
						 + "  ("
						 + "   SELECT"
						 + "    a.MODIARY_ID AS modiaryId,"
						 + "    a.MODIARY_CODE AS modiaryCode,"
						 + "    a.MODIARY_UNIT AS modiaryUnit,"
						 + "    a.MODIARY_NAME AS modiaryName,"
						 + "    a.ACTOR AS actor,"
						 + "    a.FINE AS fine,"
						 + "    a.MIDDLE AS middle,"
						 + "    a.BAD AS bad,"
						 + "    a.TOTAL AS total"
						 + "   FROM"
						 + "    cis_es_modiary a"
						 + "    ) aa"
						 + "    WHERE 1 = 1";
		
		return selectBySql(sqlString);
	}

}
