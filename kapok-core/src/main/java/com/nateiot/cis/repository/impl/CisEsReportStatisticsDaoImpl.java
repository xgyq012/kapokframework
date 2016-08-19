package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisEsReportStatisticsDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 服务办事 -- 民情日记
 * 
 *  @author Guohw
 */
public class CisEsReportStatisticsDaoImpl extends BaseDaoImpl
		implements CisEsReportStatisticsDaoPlus{

	/**
	 * 民情日记统计 
	 */
	@Override
	public Page<Map<String, Object>> diarySearch(String timeGte, String timeLte, 
			Map<String, SearchFilter> conditions, Pageable pageable, String first, String last) {
		
		ResultFields fields = new ResultFields();
		fields.addField("unitsId")
		.addField("writer")
		.addField("realName")
		.addField("LEVELA")
		.addField("LEVELB")
		.addField("LEVELC")
		.addField("LEVELD")
		.addField("allscore");
		
		String sql2 = "  WHERE 1 = 1  ";
		String sql3 = "  GROUP BY t.WRITER  ";
		String sqlString = "";
		
		if(!StringUtils.isEmpty(timeGte)){
				sql2 += " AND t.DIARY_DATE > '" + timeGte + " 00:00:00'";
			
			if(!StringUtils.isEmpty(timeLte)){
				sql2 += " AND t.DIARY_DATE < '" + timeLte + " 23:59:59'";
			}
		}else if(StringUtils.isEmpty(timeGte)){
			sql2 += " AND t.DIARY_DATE > '" + first + " 00:00:00'" 
				  + " AND t.DIARY_DATE < '" + last + " 23:59:59'";
			
			if(!StringUtils.isEmpty(timeLte)){
				sql2 += " AND t.DIARY_DATE < '" + timeLte + " 23:59:59'";
			}
		}else if(!StringUtils.isEmpty(timeLte)){
			sql2 += " AND t.DIARY_DATE < '" + timeLte + " 23:59:59'";
		}
		
//		sqlString = "  SELECT"
//				+"      unitsId,"
//				+"      writer,"
//				+"      realName,"
//				+"      LEVELA,"
//				+"      LEVELB,"
//				+"      LEVELC,"
//				+"      LEVELD,"
//				+"      ("
//				+"          ("
//				+"              SELECT"
//				+"                  IF("
//				+"                      ce.score IS NULL,"
//				+"                      0,"
//				+"                      ce.score"
//				+"                  )"
//				+"              FROM"
//				+"                  cis_es_integal ce"
//				+"              WHERE"
//				+"                  ce.INTEGRAL_CODE = 'diaryScoreA'"
//				+"          ) * LEVELA +("
//				+"              SELECT"
//				+"                  IF("
//				+"                      ce.score IS NULL,"
//				+"                      0,"
//				+"                      ce.score"
//				+"                  )"
//				+"              FROM"
//				+"                  cis_es_integal ce"
//				+"              WHERE"
//				+"                  ce.INTEGRAL_CODE = 'diaryScoreB'"
//				+"          ) * LEVELB +("
//				+"              SELECT"
//				+"                  IF("
//				+"                      ce.score IS NULL,"
//				+"                      0,"
//				+"                      ce.score"
//				+"                  )"
//				+"              FROM"
//				+"                  cis_es_integal ce"
//				+"              WHERE"
//				+"                  ce.INTEGRAL_CODE = 'diaryScoreC'"
//				+"          ) * LEVELC +("
//				+"              SELECT"
//				+"                  IF("
//				+"                      ce.score IS NULL,"
//				+"                      0,"
//				+"                      ce.score"
//				+"                  )"
//				+"              FROM"
//				+"                  cis_es_integal ce"
//				+"              WHERE"
//				+"                  ce.INTEGRAL_CODE = 'diaryScoreD'"
//				+"          ) * LEVELD"
//				+"      ) AS allscore"
//				+"  FROM"
//				+"      ("
//				+"          SELECT"
//				+"              t.UNITS_ID AS unitsId,"
//				+"              t.WRITER AS writer,"
//				+"              t.REALNAME AS realName,"
//				+"              t.DIARY_DATE AS diaryDate,"
//				+"              SUM( CASE t.ESTIMATE_LEVEL WHEN 'diaryScoreA' THEN t.acount ELSE 0 END ) AS LEVELA,"
//				+"              SUM( CASE t.ESTIMATE_LEVEL WHEN 'diaryScoreB' THEN t.acount ELSE 0 END ) AS LEVELB,"
//				+"              SUM( CASE t.ESTIMATE_LEVEL WHEN 'diaryScoreC' THEN t.acount ELSE 0 END ) AS LEVELC,"
//				+"              SUM( CASE t.ESTIMATE_LEVEL WHEN 'diaryScoreD' THEN t.acount ELSE 0 END ) AS LEVELD"
//				+"          FROM"
//				+"              ("
//				+"                  SELECT"
//				+"                      sr.UNITS_ID,"
//				+"                      sr.writer,"
//				+"                      u.REALNAME,"
//				+"                      sr.ESTIMATE_LEVEL,"
//				+"                      sr.DIARY_DATE,"
//				+"                      COUNT( * ) AS acount"
//				+"                  FROM"
//				+"                      CIS_SW_CONDITIONS_DIARY sr,"
//				+"                      gxwl_sys_user u"
//				+"                  WHERE"
//				+"                      sr.writer = u.USER_ID"
//				+"                  GROUP BY"
//				+"                      sr.writer,"
//				+"                      sr.ESTIMATE_LEVEL"
//				+"              ) t"
//				+sql2
//				+sql3
//				+"      ) tt";
		
		sqlString = "  SELECT"
				+"      unitsId,"
				+"      writer,"
				+"      realName,"
//				+"      diaryDate,"
				+"      LEVELA,"
				+"      LEVELB,"
				+"      LEVELC,"
				+"      LEVELD,"
				+"      ("
				+"         LEVELA+LEVELB+LEVELC+LEVELD"
				+"      ) AS allscore"
				+"  FROM"
				+"      ("
				+"          SELECT"
				+"              t.UNITS_ID AS unitsId,"
				+"              t.WRITER AS writer,"
				+"              t.REALNAME AS realName,"
				+"              t.DIARY_DATE AS diaryDate,"
				+"              SUM( CASE t.ESTIMATE_LEVEL WHEN 'diaryScoreA' THEN t.acount ELSE 0 END ) AS LEVELA,"
				+"              SUM( CASE t.ESTIMATE_LEVEL WHEN 'diaryScoreB' THEN t.acount ELSE 0 END ) AS LEVELB,"
				+"              SUM( CASE t.ESTIMATE_LEVEL WHEN 'diaryScoreC' THEN t.acount ELSE 0 END ) AS LEVELC,"
				+"              SUM( CASE t.ESTIMATE_LEVEL WHEN 'diaryScoreD' THEN t.acount ELSE 0 END ) AS LEVELD"
				+"          FROM"
				+"              ("
				+"                  SELECT"
				+"                      sr.UNITS_ID,"
				+"                      sr.writer,"
				+"                      u.REALNAME,"
				+"                      sr.ESTIMATE_LEVEL,"
				+"                      sr.DIARY_DATE,"
				+"                      COUNT( * ) * al.SCORE AS acount"
				+"                  FROM"
				+"                      CIS_SW_CONDITIONS_DIARY sr,"
				+"                      gxwl_sys_user u  , cis_es_integal al "
				+"                  WHERE"
				+"                      sr.writer = u.USER_ID and sr.ESTIMATE_LEVEL=al.INTEGRAL_CODE"
				+"                  GROUP BY"
				+"                      sr.writer,"
				+"                      sr.ESTIMATE_LEVEL"
				+"              ) t"
				+sql2
				+sql3
				+"      ) tt"
				+"  WHERE 1=1";
		
		return selectBySqlPageable(sqlString, conditions , pageable , fields);
	}

	/**
	 * 积分统计 
	 */
	@Override
	public Page<Map<String, Object>> scoreSearch(String timeGte, String timeLte, 
			Map<String, SearchFilter> conditions, Pageable pageable, String first, String last) {
		String sql2 = "";
		String sql3 = "";
		String sqlString = "";
		
		if(!StringUtils.isEmpty(timeGte)){
			sql2 = " AND ES.DETAIL_TIME > '" + timeGte + " 00:00:00'";
			
			if(!StringUtils.isEmpty(timeLte)){
				sql2 += " AND ES.DETAIL_TIME < '" + timeLte + " 23:59:59'";
			}
		}else if(StringUtils.isEmpty(timeGte)){
			sql2 += " AND es.DETAIL_TIME > '" + first + " 00:00:00'" 
					  + " AND es.DETAIL_TIME < '" + last + " 23:59:59'";
				
				if(!StringUtils.isEmpty(timeLte)){
					sql2 += " AND es.DETAIL_TIME < '" + timeLte + " 23:59:59'";
				}
			}else if(!StringUtils.isEmpty(timeLte)){
			sql2 = " AND ES.DETAIL_TIME < '" + timeLte + " 23:59:59'";
		}
		
//		sql3 = "AND M.MESH_ID IN ('" + ids + "')";
		
		sqlString = "  SELECT"
				+"      userId,"
				+"      realName,"
				+"      otel,"
				+"      htel,"
				+"      score,"
				+"      meshName,"
				+"      meshFullName,"
				+"      meshId"
				+"  FROM"
				+"      ("
				+"          SELECT"
				+"              es.USER_ID AS userId,"
				+"              u.REALNAME AS realName,"
				+"              u.OTEL AS otel,"
				+"              u.HTEL AS htel,"
				+"              SUM( es.SCORE ) AS score,"
				+"              m.MESH_NAME AS meshName,"
				+"              m.MESH_FULL_NAME AS meshFullName,"
				+"              m.MESH_ID AS meshId"
				+"          FROM"
				+"              cis_es_integal_detail es,"
				+"              gxwl_sys_user u,"
				+"              cis_bm_community_mesh_user mu,"
				+"              cis_bm_community_mesh m"
				+"          WHERE"
				+"              es.USER_ID = u.USER_ID"
				+"              AND mu.MESH_ID = m.MESH_ID"
				+"              AND mu.USER_ID = u.USER_ID"
				+"              AND M.MESH_ID IN (1, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 94, 95, 96, 97, 98, 99, 100)"
//				+"              AND ES.DETAIL_TIME BETWEEN '2016-01-01' AND '2016-12-31'"
//				+sql3
				+sql2
				+"          GROUP BY"
				+"              es.USER_ID"
				+"      ) tt"
				+"    WHERE 1=1";

		return selectBySqlPageable(sqlString, conditions, pageable);
	}
	
}
