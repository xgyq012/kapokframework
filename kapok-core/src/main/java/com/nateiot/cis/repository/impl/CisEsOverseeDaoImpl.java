package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisEsOverseeDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 * 考核督办 -- 事件督办
 * 
 *  @author guohuawen
 */
public class CisEsOverseeDaoImpl extends BaseDaoImpl implements
		CisEsOverseeDaoPlus{
	
	/**
	 * 查询 
	 */
	@Override
	public Page<Map<String, Object>> search(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		String sqlString = "   SELECT"
				+"       ev.ENROLL_ID AS enrollId,"
				+"       ev.ENROLL_CODE AS enrollCode,"
				+"       ev.ENROLL_TITLE AS enrollTitle,"
				+"       ev.REMARK AS remark,"
				+"       ev.UNITS_ID AS unitsId,"
				+"       ev.ENROLL_SOURCE AS enrollSource,"
				+"       ev.ENROLL_PLACE AS enrollPlace,"
				+"       ev.ENROLL_TIME AS enrollTime,"
				+"       ev.CLIENT AS client,"
				+"       ev.CLIENT_PHONE AS clientPhone,"
				+"       ev.ENROLL_TYPE AS enrollType,"
				+"       ev.ENROLL_CATEGORY AS enrollCate,"
				+"       ev.INVOLE_PEOPLE AS involePeo,"
				+"       ev.ENROLL_STATUS AS enrollStatus,"
				+"       ev.SUGGESTION AS suggestion,"
				+"       ev.EVALUATE AS evaluate,"
				+"       ev.EVALUATE_LAST AS evaluateLast,"
				+"       ev.LON AS lon,"
				+"       ev.LAT AS lat"
				+"   FROM"
				+"       cis_sw_event_enroll ev"
				+"   WHERE"
				+"       ev.DEL_SIGN = 'N'"
				+"       AND ev.ENROLL_STATUS <> 'estimate'"
				+"       AND ev.ENROLL_STATUS <> ''  ";
		Page<Map<String, Object>> page = selectBySqlPageable(sqlString, conditions, pageable);
		for(Map<String, Object> map : page.getContent()){
			if(!StringUtils.isEmpty(map.get("enrollTime").toString())){
				map.put("enrollTime", map.get("enrollTime")
						.toString().subSequence(0, 19));
			}
		}
		
		return page;
	}


}
