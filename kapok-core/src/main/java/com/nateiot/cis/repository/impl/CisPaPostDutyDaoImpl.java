package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisPaPostDutyDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 党务建设 -- 党员设岗定责登记表
 * 
 * @author Guohw
 */
public class CisPaPostDutyDaoImpl 
		extends BaseDaoImpl implements CisPaPostDutyDaoPlus{
	
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
	
	/**
	 * 查询 
	 */
	@Override
	public Page<Map<String, Object>> searchBy(Map<String, SearchFilter> conditions,
			Pageable pageable) {
		
		ResultFields fields = new ResultFields();
		fields.addField("dutyId")
		.addField("memberId")
		.addField("unitsId")
		.addField("speciality")
		.addField("postName")
		.addField("workDuty")
		.addField("situation")
		.addField("grassroots")
		.addField("branch")
		.addField("remark")
		.addField("houseHolderId")
		.addField("name")
		.addField("age")
		.addField("sex")
		.addField("socialJob");
		
		String sqlString = "  SELECT"
				+"      pd.DUTY_ID as dutyId,"
				+"      pd.MEMBER_ID as memberId,"
				+"      pd.UNITS_ID as unitsId,"
				+"      pd.SPECIALITY as speciality,"
				+"      pd.POST_NAME as postName,"
				+"      pd.WORK_DUTY as workDuty,"
				+"      pd.SITUATION as situation,"
				+"      pd.GRASSROOTS as grassroots,"
				+"      pd.BRANCH as branch,"
				+"      pd.REMARK as remark, "
				+"      hh.HOUSEHOLDER_ID as houseHolderId,"
				+"      hh.HOUSEHOLDER_NAME as name,"
				+"      hh.AGE as age,"
				+"      hh.SEX as sex,"
				+"      hh.SOCIAL_JOB as socialJob"
				+"  FROM"
				+"      CIS_PA_POST_DUTY pd, cis_bm_householder hh"
				+"  WHERE"
				+"      pd.DEL_SIGN = 'N'"
				+"      AND hh.HOUSEHOLDER_ID = pd.MEMBER_ID";
				
		return selectBySqlPageable(sqlString, conditions , pageable , fields);
	}

}
