package com.nateiot.cis.repository.impl;

import java.util.Map;

import com.nateiot.cis.repository.CisEsIntegalDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 *  考核督办 -- 积分管理
 * 
 * @author Administrator
 *
 */
public class CisEsIntegalDaoImpl extends BaseDaoImpl implements
		CisEsIntegalDaoPlus {
	
	/**
	 * 通过积分配置编码，查找分值
	 * 
	 *  @param integalCode
	 *  @return
	 */
	@Override
	public Map<String, Object> findScoreByIntegalCode(String integalCode) {
		String sqlString = "  SELECT"
				+"      a.SCORE as score"
				+"  FROM"
				+"      cis_es_integal a"
				+"  WHERE"
				+"      a.INTEGRAL_CODE = '" + integalCode + "'";
		return selectOneBySql(sqlString);
	}

}
