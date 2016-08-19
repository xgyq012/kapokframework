package com.nateiot.cis.repository.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisEsIntegalDetailDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 * 考核督办 -- 积分明细记录
 * 
 *  @author Guohw
 */
public class CisEsIntegalDetailDaoImpl extends BaseDaoImpl implements
		CisEsIntegalDetailDaoPlus{

	@Override
	public Page<Map<String, Object>> searchDetail(String timeGte,
			String timeLte, Map<String, SearchFilter> conditions,
			Pageable pageable) {
		
		String sqlString = "";
		String sql2 = "";
		
		if(!StringUtils.isEmpty(timeGte)){
			sql2 = " AND a.DETAIL_TIME > '" + timeGte + " 00:00:00'";
			
			if(!StringUtils.isEmpty(timeLte)){
				sql2 += " AND a.DETAIL_TIME < '" + timeLte + " 23:59:59'";
			}
		}else if(!StringUtils.isEmpty(timeLte)){
			sql2 = " AND a.DETAIL_TIME < '" + timeLte + " 23:59:59'";
		}
		
		sqlString = "   SELECT"
				+"       a.DETAIL_ID AS detailId,"
				+"       a.USER_ID AS userId,"
				+"       a.USER_NAME AS userName,"
				+"       a.REAL_NAME AS realName,"
				+"       a.DETAIL_TYPE AS detailType,"
				+"       a.SCORE AS score,"
				+"       a.DETAIL_TIME AS detailTime,"
				+"       a.VOUCHER_ID AS voucherId,"
				+"       a.VOUCHER_TYPE AS voucherType,"
				+"       a.REMARK AS remark,"
				+"       a.CREATE_BY as createBy,"
				+"       a.CREATE_TIME as createTime,"
				+"       a.LAST_UPDATE_BY as lastUpdateBy,"
				+"       a.LAST_UPDATE_TIME as lastUpdateTime"
				+"   FROM"
				+"       cis_es_integal_detail a"
				+"   WHERE"
				+"       1 = 1"
				+ sql2;
		
		Page<Map<String, Object>> page = selectBySqlPageable(sqlString, conditions, pageable);
		List<Map<String, Object>> list = page.getContent();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Map<String, Object> rows : list){
			if(rows.get("detailTime") != null){
				String detailTime = formatter.format(rows.get("detailTime"));
				rows.put("detailTime", detailTime);
			}
			
		}
		
		return page;
	}

	/**
	 * 查询用户当天登录次数 
	 */
	@Override
	public List<Map<String, Object>> searchLoginTimes(Integer userId) {
		String str = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()) + "  00:00:00";
		String str2 = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()) + "  23:59:59";
		String sqlString = "   SELECT"
				+"       de.DETAIL_ID,"
				+"       de.USER_ID,"
				+"       de.DETAIL_TYPE,"
				+"       de.DETAIL_TIME"
				+"   FROM"
				+"       cis_es_integal_detail de"
				+"   WHERE"
				+"       de.USER_ID = '" + userId +"'"
//				+"       AND de.DETAIL_TIME = '"+ str +"'";
				+"       AND de.DETAIL_TIME > '" + str + "'"
				+"       AND de.DETAIL_TIME < '" + str2 + "'";

		return selectBySql(sqlString);
	}

}
