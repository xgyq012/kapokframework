package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisBmCsxjryDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;

public class CisBmCsxjryDaoImpl extends BaseDaoImpl implements CisBmCsxjryDaoPlus {
	
	public Page<Map<String, Object>> getCsxjryHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable){
		
		String sqlString = " SELECT "
				    +" h.HOUSEHOLDER_ID AS householderId,"
				    +" h.HOUSEHOLDER_NAME  AS householderName,"
				    +" h.AGE AS age,"
				    +" h.BUILD_ID AS buildId,"
				    +" h.HOUSE_ID AS houseId,"
				    +" h.COM_ID AS comId,"
				    +" h.CARD_CODE AS cardCode,"
				    +" h.SEX AS sex,"
				    +" h.BIRTH_DATE AS birthDate,"
				    +" h.NATIONALITY AS nationality,"
				    +" h.ORG_ID AS orgId,"
				    +" h.EDU_LEVEL AS eduLevel,"
				    +" h.GLLX as gllx,"
				    +" h.MARITAL_STATUS as maritalStatus,"
				    +" h.MARITAL_DATA as maritalData,"
				    +" h.HEALTH_STATUS as healthStatus,"
				    +" h.CALL_PHONE as callPhone,"
				    +" h.JOB_NAME as jobName,"
				    +" h.RELIGION as religion,"
				    +" h.ZJXY as zjxy,"
				    +" h.SOCIAL_JOB as socialJob,"
				    +" h.UNIT as unit,"
				    +" h.DUTY_NAME as dutyName,"
				    +" h.SPECIALTY as specialty,"
				    +" h.BLOOD_TYPE as bloodType,"
				    +" h.VETERAN_STATUS as veteranStatus ,"
				    +" h.HOUSEHOLD_TYPE AS householdType,"
				    +" h.HOUSEHOLD_ADDRESS AS householdAddress,"
				    +" h.DWELL_ADDRESS AS dwellAddress,"
				    +" h.SG_HEIGHT as sgHeight,"
				    +" h.HOUSING_AREA as housingArea,"
				    +" h.DOORPLATE as doorplate ,"
				    +" h.IS_VOLUNTEER as isVolunteer ,"
				    +" h.POLITICS_STATUS as politicsStatus,"
				    +" h.REMARK as remark,"
				    +" h.ISHAVE_HOUSE as ishaveHouse,"
				    +" h.HOUSEHOLDER_RELATION as householderRelation,"
				    +" h.PHOTOFILE_ID as photofileId,"
				    +" h.NATIVE_PLACE as nativePlace,"
				    +" h.DOC_SHOWNAME as docShowname,"
				    +" h.CREATE_BY AS createBy,"
				    +" h.CREATE_TIME AS createTime,"
				    +" h.LAST_UPDATE_BY  AS lastUpdateBy,"
				    +" h.LAST_UPDATE_TIME  AS lastUpdateTime "
				    +" FROM "
				        +" cis_bm_householder h  "
				        +" right JOIN CIS_BM_CSXJRY l ON l.HOUSEHOLDER_ID = h.HOUSEHOLDER_ID "
				    +" where  h.del_sign <> 'Y' " ;
		
		 return selectBySqlPageable(sqlString, conditions, pageable);
	}
	
	
}
