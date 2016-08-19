package com.nateiot.cis.repository.impl;

import java.util.Map;

import com.nateiot.cis.repository.CisBmUnitsDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 单位信息
 * 
 * @author Administrator
 *
 */
public class CisBmUnitsDaoImpl extends BaseDaoImpl implements
        CisBmUnitsDaoPlus {

	@Override
	public Map<String, Object> count(String meshIds) {
		ResultFields fields = new ResultFields();
		fields.addField("sydwzs")//单位总数
		.addField("xzjg")    //行政机关
		.addField("allqy")   //所有企业		
		.addField("syqy")    //私营企业
		.addField("wzqy")    //外资企业
		.addField("jtqy")    //集体企业
		.addField("qtqy");  //其他企业

		//.addField("gyqy");  //国有企业，你tm对国有企业有意见？
		
		String sqlString = "SELECT" 
				+" COUNT(u.UNIT_ID) as 'sydwzs', "
				+" SUM(CASE u.DWXZ WHEN 'administrative' THEN 1 ELSE 0 END) AS 'xzjg', "
				+" SUM(CASE u.DWXZ WHEN 'others' OR 'private' OR 'foreign' OR 'collective' THEN 1 ELSE 0 END) AS 'allqy', "
				+" SUM(CASE u.DWXZ WHEN 'private' THEN 1 ELSE 0 END) AS 'syqy', "
				+" SUM(CASE u.DWXZ WHEN 'foreign' THEN 1 ELSE 0 END) AS 'wzqy', "
				+" SUM(CASE u.DWXZ WHEN 'collective' THEN 1 ELSE 0 END) AS 'jtqy', "
				+" SUM(CASE u.DWXZ WHEN 'others' THEN 1 ELSE 0 END) AS 'qtqy' "
				+" FROM cis_bm_units u "
			    +" where u.DEL_SIGN = 'N' and u.ORG in ("+ meshIds + ")";
		return this.selectOneBySql(sqlString, fields);
	}

}
