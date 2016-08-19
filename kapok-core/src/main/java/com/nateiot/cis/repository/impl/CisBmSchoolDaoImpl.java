package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisBmSchoolDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 学校信息 
 * 
 * @author Administrator
 *
 */
public class CisBmSchoolDaoImpl extends BaseDaoImpl implements
		CisBmSchoolDaoPlus {

	@Override
	public Page<Map<String, Object>> querySchool(
			Map<String, SearchFilter> conditions, Pageable pageable) {
				ResultFields fields = new ResultFields();
				fields.addField("schoolId")
				.addField("org")
				.addField("xxmc")
				.addField("dz")
				.addField("lon")
				.addField("lat");
				String sqlString = " SELECT "
									+ " s.SCHOOL_ID as schoolId, "
									+ " s.SSJG as org, "
									+ " s.XXMC as xxmc, "
									+ " s.DZ as dz, "
									+ " s.LON as lon, "
									+ " s.LAT as lat "
									+ " FROM "
								    + " cis_bm_school s  "
									+" where 1=1 ";
				
				return this.selectBySqlPageable(sqlString,conditions," s.SCHOOL_ID " , pageable,fields);
			}

}
