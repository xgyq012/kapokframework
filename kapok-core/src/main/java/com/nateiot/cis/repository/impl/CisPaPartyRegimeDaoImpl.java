package com.nateiot.cis.repository.impl;

import java.util.Map;

import com.nateiot.cis.repository.CisPaPartyRegimeDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 * 党务建设 -- 党务公开制度
 * 
 * @author Guohw
 *
 */
public class CisPaPartyRegimeDaoImpl extends BaseDaoImpl implements
		CisPaPartyRegimeDaoPlus {

	/**
	 * 党务建设 -- 工作台 
	 */
	@Override
	public Map<String, Object> findTheMaxPartyRegimeId() {
		String sqlString = "   SELECT"
				+"       pr.REGIME_ID AS regimeId,"
				+"       pr.REGIME_CODE AS regimeCode,"
				+"       pr.REGIME_TITLE AS regimeTitle,"
				+"       pr.REMARK AS remark"
				+"   FROM"
				+"       cis_pa_party_regime pr"
				+"   WHERE"
				+"       pr.REGIME_ID IN("
				+"           SELECT"
				+"               MAX( cp.REGIME_ID ) AS regime"
				+"           FROM"
				+"               cis_pa_party_regime cp"
				+"           WHERE"
				+"               cp.REGIME_TYPE = 'puSystem'"
				+"               AND cp.DEL_SIGN = 'N'"
				+"       )";
		return selectOneBySql(sqlString);
	}

}
