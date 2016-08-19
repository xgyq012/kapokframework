package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisBmPartyMemberInfoDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;


public class CisBmPartyMemberInfoDaoImpl extends BaseDaoImpl implements CisBmPartyMemberInfoDaoPlus {
	
	@Override
	public Page<Map<String, Object>>  search (Map<String, SearchFilter> conditions, Pageable pageable){
		
		String sqlString = " SELECT "
			    +" h.ORG_ID as orgId , "
			    +" h.HOUSEHOLDER_NAME as householderName, "
			    +" h.SEX as sex, "
			    +" h.BIRTH_DATE as birthDate, "
			    +" h.NATIONALITY as nationality, "
			    +" h.EDU_LEVEL as eduLevel, "
			    +" h.POLITICS_STATUS as politicsStatus, "
			    +" h.HOUSEHOLD_TYPE as householdType, "
			    +" h.MARITAL_STATUS as maritalStatus, "
			    +" h.HOUSEHOLD_ADDRESS as householdAddress, "
			    +" h.CARD_CODE as cardCode, "
			    +" h.DUTY_NAME as dutyName ,"
			    +" h.CALL_PHONE as callPhone ,"
			    +" h.DWELL_ADDRESS as dwellAddress ,"
			    +" p.PARTY_ID as partyId, "
			    +" p.HOUSEHOLDER_ID as householderId, "
			    +" p.ORGANIZATION_TYPE as organizationType, "
			    +" p.STRAIGHT_PARTY_MEMBER as straightPartyMember, "
			    +" p.FLOATING_PARTY_MEMBERS as floatingPartyMembers, "
			    +" p.DIFFICULT_PARTY_MEMBERS as difficultPartyMembers, "
			    +" p.OLD_PARTY_MEMBERS as oldPartyMembers, "
			    +" p.APPRAISAL_RESULT as appraisalResult, "
			    +" p.REWARDS_RESULT as rewardsResult"
			+" FROM "
			    +" CIS_BM_HOUSEHOLDER h LEFT JOIN CIS_BM_PARTY_MEMBER_INFO P "
			        +" ON h.HOUSEHOLDER_ID = p.PARTY_ID "
			+" WHERE "
			    +" EXISTS( "
			        +" SELECT "
			            +" 1 "
			        +" FROM "
			            +" GXWL_SYS_DICT_L L LEFT JOIN GXWL_SYS_DICT_H DH "
			                +" ON DH.dict_type_id = L.DICT_TYPE_ID "
			        +" WHERE "
			            +" DH.DICT_TYPE_CODE = 'politicsStatus' "
			            +" AND L.DICT_NAME = '党员' "
			    +" ) and 1=1  " ;

		   
		   return selectBySqlPageable(sqlString, conditions, pageable);
	}
}
