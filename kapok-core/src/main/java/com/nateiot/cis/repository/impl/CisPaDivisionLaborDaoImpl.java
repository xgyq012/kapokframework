package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisPaDivisionLaborDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 党务建设 -- 两委分工
 * 
 *  @author Guohw
 */
public class CisPaDivisionLaborDaoImpl extends BaseDaoImpl
		implements CisPaDivisionLaborDaoPlus{
	
	/**
	 * 查找党员 
	 */
	public Page<Map<String, Object>> findMember(Map<String, SearchFilter> conditions, Pageable pageable){
		ResultFields fields = new ResultFields();
		fields.addField("houseHolderId")
		.addField("houseHolderName")
		.addField("age")
		.addField("sex")
		.addField("eduLevel")
		.addField("socialJob");
		
		String sqlString = "   SELECT"
				+"   	 hh.HOUSEHOLDER_ID as houseHolderId,"
				+"       hh.HOUSEHOLDER_NAME as houseHolderName,"
				+"       hh.AGE as age,"
				+"       hh.SEX as sex,"
				+"       hh.EDU_LEVEL as eduLevel,"
				+"       hh.SOCIAL_JOB as socialJob"
				+"   FROM"
				+"       cis_bm_householder hh"
				+"   WHERE"
				+"       hh.POLITICS_STATUS = 3";
		
		return selectBySqlPageable(sqlString, conditions, pageable, fields);
	}

}
