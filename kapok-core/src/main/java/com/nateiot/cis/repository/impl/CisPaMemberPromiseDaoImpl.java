package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisPaMemberPromiseDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 党务建设 -- 党员公开承诺活动登记表
 * 
 * @author Guohw
 */
public class CisPaMemberPromiseDaoImpl extends BaseDaoImpl 
		implements CisPaMemberPromiseDaoPlus{
	
	/**
	 * 查找党员 
	 */
	public Page<Map<String, Object>> findMember(Map<String, SearchFilter> conditions, Pageable pageable){
		ResultFields fields = new ResultFields();
		fields.addField("houseHolderId")
		.addField("houseHolderName")
		.addField("age")
		.addField("sex")
		.addField("eduLevel")
		.addField("socialJob");
		
		String sqlString = "   SELECT"
				+"   	 hh.HOUSEHOLDER_ID as houseHolderId,"
				+"       hh.HOUSEHOLDER_NAME as houseHolderName,"
				+"       hh.AGE as age,"
				+"       hh.SEX as sex,"
				+"       hh.EDU_LEVEL as eduLevel,"
				+"       hh.SOCIAL_JOB as socialJob"
				+"   FROM"
				+"       cis_bm_householder hh"
				+"   WHERE"
				+"       hh.POLITICS_STATUS = 3";
		
		return selectBySqlPageable(sqlString, conditions, pageable, fields);
	}

	
	
	/**
	 * 查询 
	 */
	@Override
	public Page<Map<String, Object>> searchBy(String timeGte,
			String timeLte, Map<String, SearchFilter> conditions,
			Pageable pageable) {
		
		ResultFields fields = new ResultFields();
		fields.addField("promiseId")
		.addField("unitsId")
		.addField("writeDate")
		.addField("memberId")
		.addField("village")
		.addField("branch")
		.addField("promiseItem")
		.addField("situation")
		.addField("suggestion")
		.addField("review")
		.addField("houseHolderId")
		.addField("age")
		.addField("sex")
		.addField("name");
		
		
		String sqlString = " ";
		String sql2 = " ";
		
		if(timeGte.length() != 0 && timeLte.length() != 0 && timeGte.equals(timeLte)){
			sql2 += " AND mp.WRITE_DATE = '" + timeGte + "'";
		} else if (!StringUtils.isEmpty(timeGte)) {
			sql2 += " AND mp.WRITE_DATE > '" + timeGte + " 00:00:00'";

			if (!StringUtils.isEmpty(timeLte)) {
				sql2 += " AND mp.WRITE_DATE < '" + timeLte + " 23:59:59'";
			}
		} else if (StringUtils.isEmpty(timeGte)) {

			if (!StringUtils.isEmpty(timeLte)) {
				sql2 += " AND mp.WRITE_DATE < '" + timeLte + " 23:59:59'";
			}
		} else if (!StringUtils.isEmpty(timeLte)) {
			sql2 += " AND mp.WRITE_DATE < '" + timeLte + " 23:59:59'";
		}
		
		 sqlString = "   SELECT"
				 +"       mp.PROMISE_ID AS promiseId,"
				 +"       mp.UNITS_ID AS unitsId,"
				 +"       mp.WRITE_DATE AS writeDate,"
				 +"       mp.MEMBER_ID AS memberId,"
				 +"       mp.VILLAGE AS village,"
				 +"       mp.BRANCH AS branch,"
				 +"       mp.PROMISE_ITEM AS promiseItem,"
				 +"       mp.SITUATION AS situation,"
				 +"       mp.SUGGESTION AS suggestion,"
				 +"       mp.REVIEW AS review,"
				 +"       hh.HOUSEHOLDER_ID as houseHolderId,"
				 +"       hh.AGE as age,"
				 +"       hh.SEX as sex,"
				 +"       hh.HOUSEHOLDER_NAME as name"
				 +"   FROM"
				 +"       CIS_PA_MEMBER_PROMISE mp,cis_bm_householder hh"
				 +"   WHERE"
				 +"       mp.DEL_SIGN = 'N'"
				 +"       AND hh.HOUSEHOLDER_ID = mp.MEMBER_ID"
				 +sql2;
				
		return selectBySqlPageable(sqlString, conditions , pageable , fields);
	}

}
