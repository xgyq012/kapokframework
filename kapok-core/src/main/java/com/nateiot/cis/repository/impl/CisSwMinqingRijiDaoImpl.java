package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisSwMinqingRijiDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
/**
 * 民情日记
 * @author xiewenhua
 *
 */
public class CisSwMinqingRijiDaoImpl extends BaseDaoImpl implements CisSwMinqingRijiDaoPlus{
	@Override	
	public Page<Map<String, Object>>  doSearchBySql (Map<String, SearchFilter> conditions, Pageable pageable){
		
		   String sqlString = "SELECT "
					    +"b.BUILD_ID as buildId,"
					    +"b.ORG,"
					    +"b.COM_ID,"
					    +"b.LD_CODE as mId,"
					    +"b.JMS,"
					    +"b.ZHS,"
					    +"b.FHCS,"
					    +"b.FDCS,"
					    +"b.QSRY,"
					    +"b.WSSSQK,"
					    +"b.DHBJ,"
					    +"b.DZJK,"
					    +"b.FBQK,"
					    +"b.B_NAME,"
					    +"b.LON,"
					    +"b.LAT,"
					    +"b.CREATE_BY,"
					    +"b.CREATE_TIME,"
					    +"b.LAST_UPDATE_BY,"
					    +"b.LAST_UPDATE_TIME,"
						+" c.COMMUNITY_NAME as communityName "
						+" FROM "
						+" CIS_BM_BUILDING_MSG b "
						+"    LEFT JOIN "
						+" cis_bm_community_msg c "
						+"  ON b.M_ID = c.M_ID "; 
		   
		   return selectBySqlPageable(sqlString, conditions, pageable);
	}
}
