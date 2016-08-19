package com.nateiot.cis.repository.impl;

import java.util.Iterator;
import java.util.Map;

import com.nateiot.cis.repository.CisBmFireControlDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 消防信息
 * 
 * @author Administrator
 *
 */
public class CisBmFireControlDaoImpl extends BaseDaoImpl implements
        CisBmFireControlDaoPlus {

	@Override
	public Map<String, Object> count(String meshIds) {
		ResultFields fields = new ResultFields();
		fields.addField("CONGESTED")
		.addField("OVERLOAD")    
		.addField("EXPLOSIVES")    
		.addField("DAMAGE")    
		.addField("STAIRS");    
		
		String sqlString = "SELECT" 
				+" SUM(CASE fc.CONGESTED WHEN 'Y' THEN 1 ELSE 0 END) AS 'CONGESTED', "
				+" SUM(CASE fc.OVERLOAD WHEN 'Y' THEN 1 ELSE 0 END) AS 'OVERLOAD', "
				+" SUM(CASE fc.EXPLOSIVES WHEN 'Y' THEN 1 ELSE 0 END) AS 'EXPLOSIVES', "
				+" SUM(CASE fc.DAMAGE WHEN 'Y' THEN 1 ELSE 0 END) AS 'DAMAGE', "
				+" SUM(CASE fc.STAIRS WHEN 'Y' THEN 1 ELSE 0 END) AS 'STAIRS' "
				+" FROM cis_bm_fire_control fc "
				+" WHERE (fc.DEL_SIGN is null or fc.DEL_SIGN = 'N') AND fc.ORG_ID IN ("+ meshIds + ")";
		Map<String, Object> map = this.selectOneBySql(sqlString, fields);
		
		//统计消防隐患总数  呜呜
		Integer xfyhzs = 0;
		Iterator<Object> ite = map.values().iterator();
		while (ite.hasNext()) {
			Object o = ite.next();
			xfyhzs += o == null ? 0 : Integer.parseInt(o.toString());
		}
		map.put("xfyhzs", xfyhzs);
		return map;
	}

}
