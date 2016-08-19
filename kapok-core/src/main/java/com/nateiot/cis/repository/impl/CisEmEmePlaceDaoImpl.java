package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisEmEmePlaceDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 应急管理 -- 避难场所
 * 
 * @author Administrator
 *
 */
public class CisEmEmePlaceDaoImpl extends BaseDaoImpl implements
		CisEmEmePlaceDaoPlus {

	@Override
	public Page<Map<String, Object>> queryRefuge(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		ResultFields fields = new ResultFields();
		fields.addField("emePlaceId")
		.addField("emePlaceName")
		.addField("address")
		.addField("lon")
		.addField("lat");
		String sqlString = " SELECT "
							+ " e.EMEPLACE_ID as emePlaceId, "
							+ " e.EMEPLACE_NAME as emePlaceName, "
							+ " e.ADDRESS as address, "
							+ " e.LON as lon, "
							+ " e.LAT as lat "
							+ " FROM "
						    + " CIS_EM_EMEPLACE e  "
							+ " where 1=1 "
						    + " and e.DEL_SIGN='N'";
		
		return this.selectBySqlPageable(sqlString,conditions," e.EMEPLACE_ID " , pageable,fields);
	}

}
