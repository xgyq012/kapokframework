package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisPaTrainMembersDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 党务建设 -- 党员培训情况登记表
 * 
 * @author Guohw
 */

public class CisPaTrainMembersDaoImpl extends BaseDaoImpl 
		implements CisPaTrainMembersDaoPlus{
	

	/**
	 * 查询 
	 */
	@Override
	public Page<Map<String, Object>> searchBy(String timeGte,
			String timeLte, Map<String, SearchFilter> conditions,
			Pageable pageable) {
		
		ResultFields fields = new ResultFields();
		fields.addField("trainId")
		.addField("trainDate")
		.addField("trainPlace")
		.addField("lecture")
		.addField("trainContent")
		.addField("takePart")
		.addField("mainContent");
		
		String sqlString = "";
		String sql2= "";
		
		if(timeGte.length() != 0 && timeLte.length() != 0 && timeGte.equals(timeLte)){
			sql2 += " AND tm.TRAIN_DATE = '" + timeGte + "'";
		} else if (!StringUtils.isEmpty(timeGte)) {
			sql2 += " AND tm.TRAIN_DATE > '" + timeGte + " 00:00:00'";

			if (!StringUtils.isEmpty(timeLte)) {
				sql2 += " AND tm.TRAIN_DATE < '" + timeLte + " 23:59:59'";
			}
		} else if (StringUtils.isEmpty(timeGte)) {

			if (!StringUtils.isEmpty(timeLte)) {
				sql2 += " AND tm.TRAIN_DATE < '" + timeLte + " 23:59:59'";
			}
		} else if (!StringUtils.isEmpty(timeLte)) {
			sql2 += " AND tm.TRAIN_DATE < '" + timeLte + " 23:59:59'";
		}
		 sqlString = "   SELECT"
				 +"       tm.TRAIN_ID as trainId,"
				 +"       tm.TRAIN_DATE as trainDate,"
				 +"       tm.TRAIN_PLACE as trainPlace,"
				 +"       tm.LECTURE as lecture,"
				 +"       tm.TRAIN_CONTENT as trainContent,"
				 +"       tm.TAKE_PART as takePart,"
				 +"       tm.MAIN_CONTENT as mainContent"
				 +"   FROM"
				 +"       cis_pa_train_members tm"
				 +"   WHERE"
				 +"       tm.DEL_SIGN = 'N'"
				+sql2;
		return selectBySqlPageable(sqlString, conditions , pageable , fields);
	}

}
