package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisBmBuildingMsgDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;

public class CisBmBuildingMsgDaoImpl  extends BaseDaoImpl implements CisBmBuildingMsgDaoPlus  {

	@Override	
	public Page<Map<String, Object>>  search (Map<String, SearchFilter> conditions, Pageable pageable){
		
		   String sqlString = "SELECT "
					    +"b.BUILD_ID as buildId,"
					    +"b.ORG as org,"
					    +"b.COM_ID as comId,"
					    +"b.LD_CODE as ldCode,"
					    +"b.JMS as jms,"
					    +"b.ZHS as zhs,"
					    +"b.FHCS as fhcs,"
					    +"b.FDCS as fdcs,"
					    +"b.QSRY as qsry,"
					    +"b.WSSSQK as wsssqk,"
					    +"b.DHBJ as dhbj,"
					    +"b.DZJK as dzjk,"
					    +"b.FBQK as fbqk,"
					    +"b.PHONE as phone,"
					    +"b.LD_NAME as ldName,"
					    +"b.LON as lon,"
					    +"b.LAT as lat,"
					    +"b.CREATE_BY as createrId,"
					    +"DATE_FORMA(b.CREATE_TIME,'yyy-MM-dd HH:mm:ss') as createTime,"
					    +"b.LAST_UPDATE_BY as lastUpdaterId,"
					    +"b.LAST_UPDATE_TIME as lastUpdateTime,"
						+" c.COMMUNITY_NAME as communityName "
						+" FROM "
						+" CIS_BM_BUILDING_MSG b "
						+"    LEFT JOIN "
						+" cis_bm_community_msg c "
						+"  ON b.COM_ID = c.COM_ID "; 
		   
		   return selectBySqlPageable(sqlString, conditions, pageable);
	}
}
