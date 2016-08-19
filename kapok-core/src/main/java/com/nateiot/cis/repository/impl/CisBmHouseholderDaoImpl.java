package com.nateiot.cis.repository.impl;


import com.nateiot.cis.repository.CisBmHouseholderDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public class CisBmHouseholderDaoImpl extends BaseDaoImpl  implements CisBmHouseholderDaoPlus {

	/**
	 * 获取不是户主的人员信息，已经存在关系中的用户则不查询
	 */
	public Page<Map<String, Object>> getNotHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable){
		
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
				        +" WHERE  h.del_sign <> 'Y' "
				        + "	AND h.HOUSEHOLDER_RELATION <> '1'  "
				        + " AND NOT EXISTS(SELECT 1 FROM CIS_BM_HOLDER_RELATIONSHIP r WHERE r.HOUSEHOLDER_ID=h.HOUSEHOLDER_ID )  " ;
		
		 return selectBySqlPageable(sqlString, conditions, pageable);
	}

	@Override
	public Map<String, Object> getCountResult(String meshIds) {
		ResultFields fields = new ResultFields();
		fields.addField("orgId")
		.addField("rkzs")
		.addField("czrk")
		.addField("ldrk")
		.addField("kgrk")
		.addField("lingsanrk")
		.addField("lssyslrzs")
		.addField("qssyslrzs")
		.addField("bssyslrzs")
		.addField("shaonianzs")
		.addField("bkszs")
		.addField("femalezs")
		.addField("malezs")
		.addField("nyhzs")
		.addField("fnyhzs")
		.addField("hkzs")
		.addField("nonyehu")
		.addField("feinonyehu")
		.addField("dy")
		.addField("tyzs")
		.addField("dyzs")
		.addField("ssmz")
		.addField("lnr")
		.addField("gglrzs")
		.addField("cjr")
		.addField("dbh")
		.addField("guer")
		.addField("kclr")
		.addField("lset");
		
		String sqlString = "select "
					+" h.ORG_ID as orgId, "
					+" count(h.HOUSEHOLDER_ID) as 'rkzs', "
					+" sum(case h.gllx when '4' then 1 else 0 end) as 'czrk', "
					+" sum(case when h.GLLX = '2' or h.GLLX = '3' then 1 else 0 end) as 'ldrk', "
					+" sum(case h.gllx when '1' then 1 else 0 end) as 'kgrk', "
					+" sum(case h.gllx when '5' then 1 else 0 end) as 'lingsanrk', "
					+" sum(case when h.AGE >= '60' then 1 else 0 end) as 'lssyslrzs', "
					+" sum(case when h.AGE >= '70' then 1 else 0 end) as 'qssyslrzs', "
					+" sum(case when h.AGE >= '80' then 1 else 0 end) as 'bssyslrzs', "
					+" sum(case when h.AGE < '18' then 1 else 0 end) as 'shaonianzs', "
					+" sum(case h.EDU_LEVEL when 'benke' then 1 else 0 end) as 'bkszs', "
					+" sum(case h.SEX when 'female' then 1 else 0 end) as 'femalezs', "
					+" sum(case h.SEX when 'male' then 1 else 0 end) as 'malezs', "
					+" sum(case h.HOUSEHOLD_TYPE when '1' then 1 else 0 end) as 'nyhzs', "
					+" sum(case h.HOUSEHOLD_TYPE when '2' then 1 else 0 end) as 'fnyhzs', "
					+" sum(case when ( h.HOUSEHOLDER_RELATION = '1' and h.HOUSEHOLD_TYPE = '2') or ( h.HOUSEHOLDER_RELATION = '1' and h.HOUSEHOLD_TYPE = '1') then 1 else 0 end) as 'hkzs', "
					+" sum(case when h.HOUSEHOLDER_RELATION = '1' and h.HOUSEHOLD_TYPE = '1' then 1 else 0 end) as 'nonyehu', "
					+" sum(case when h.HOUSEHOLDER_RELATION = '1' and h.HOUSEHOLD_TYPE = '2' then 1 else 0 end) as 'feinonyehu', "
					+" sum(case h.POLITICS_STATUS when '3' then 1 else 0 end) as 'dy', "
					+" sum(case h.POLITICS_STATUS when '2' then 1 else 0 end) as 'tyzs', "
					+" sum(case h.POLITICS_STATUS when '3' then 1 else 0 end) as 'dyzs', "
					+" sum(case when h.NATIONALITY!='hanzu' AND h.NATIONALITY is not null then 1 else 0 end) as 'ssmz',  "   
					+" sum(case when (h.HOUSEHOLDER_ID IN (SELECT oph.HOUSEHOLDER_ID from cis_bm_old_people_h oph)) then 1 else 0 end) as 'lnrzs', "
					+" sum(case when (h.HOUSEHOLDER_ID IN (SELECT oph.HOUSEHOLDER_ID from cis_bm_old_people_h oph WHERE oph.jzfs = '520')) then 1 else 0 end) as 'gglrzs', " 
					+" sum(case when (h.HOUSEHOLDER_ID IN (SELECT hp.HOUSEHOLDER_ID FROM cis_bm_handicapped_people hp)) then 1 else 0 end) as 'cjr', "  
					+" sum(case when (h.HOUSEHOLDER_ID IN (SELECT lp.HOUSEHOLDER_ID FROM cis_bm_low_people lp)) then 1 else 0 end) as 'dbh', "
					+" sum(case when (h.HOUSEHOLDER_ID IN (SELECT bo.HOUSEHOLDER_ID FROM cis_bm_orphan bo)) then 1 else 0 end) as 'guer', "
					+" sum(case when (h.HOUSEHOLDER_ID in (SELECT oph.HOUSEHOLDER_ID from cis_bm_old_people_h oph WHERE oph.JZFS = '522')) then 1 else 0 end) AS 'kclr', "
					+" sum(case when (h.HOUSEHOLDER_ID IN (SELECT lc.HOUSEHOLDER_ID FROM cis_bm_leftover_children lc)) then 1 else 0 end) as 'lset' "
				    +" from cis_bm_householder h  "
				    + " where (h.DEL_SIGN is null or h.DEL_SIGN = 'N') and h.ORG_ID in ("+ meshIds + ")";
		
		return this.selectOneBySql(sqlString, fields);
	}

}
