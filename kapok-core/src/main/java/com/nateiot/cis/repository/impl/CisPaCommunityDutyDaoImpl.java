package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisPaCommunityDutyDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 党务建设 -- 社区党务公开表
 * 
 * @author Guohw
 */
public class CisPaCommunityDutyDaoImpl extends BaseDaoImpl 
		implements CisPaCommunityDutyDaoPlus{

	/**
	 * 查询 
	 */
	@Override
	public Page<Map<String, Object>> searchBy(String timeGte,
			String timeLte, Map<String, SearchFilter> conditions,
			Pageable pageable) {
		
		ResultFields fields = new ResultFields();
		fields.addField("meetingId")
		.addField("beginDate")
		.addField("endDate")
		.addField("unitsId")
		.addField("puPlace")
		.addField("meetingDate")
		.addField("meetingForm")
		.addField("meetingPlace")
		.addField("joinSign")
		.addField("puContent")
		.addField("leadSign")
		.addField("monitorSign");
		
		String sqlString = "";
		String sql2= "";
		
		if(timeGte.length() != 0 && timeLte.length() != 0 && timeGte.equals(timeLte)){
			sql2 += " AND du.MEETING_DATE = '" + timeGte + "'";
		} else if (!StringUtils.isEmpty(timeGte)) {
			sql2 += " AND du.MEETING_DATE > '" + timeGte + " 00:00:00'";

			if (!StringUtils.isEmpty(timeLte)) {
				sql2 += " AND du.MEETING_DATE < '" + timeLte + " 23:59:59'";
			}
		} else if (StringUtils.isEmpty(timeGte)) {

			if (!StringUtils.isEmpty(timeLte)) {
				sql2 += " AND du.MEETING_DATE < '" + timeLte + " 23:59:59'";
			}
		} else if (!StringUtils.isEmpty(timeLte)) {
			sql2 += " AND du.MEETING_DATE < '" + timeLte + " 23:59:59'";
		}
		 sqlString = "   SELECT"
				+"       du.MEETING_ID as meetingId,"
				+"       du.BEGIN_DATE as beginDate,"
				+"       du.END_DATE as endDate,"
				+"       du.UNITS_ID as unitsId,"
				+"       du.PUPLACE as puPlace,"
				+"       du.MEETING_DATE as meetingDate,"
				+"       du.MEETING_FORM as meetingForm,"
				+"       du.MEETING_PLACE as meetingPlace,"
				+"       du.JOIN_SIGN as joinSign,"
				+"       du.PUCONTENT as puContent,"
				+"       du.LEAD_SIGN as leadSign,"
				+"       du.MONITOR_SIGN as monitorSign"
				+"   FROM"
				+"       cis_pa_community_duty du"
				+"   WHERE"
				+"       du.DEL_SIGN = 'N'"
				+sql2;
		return selectBySqlPageable(sqlString, conditions , pageable , fields);
	}

}
