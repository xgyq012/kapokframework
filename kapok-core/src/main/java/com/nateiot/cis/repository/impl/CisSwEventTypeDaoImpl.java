package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.nateiot.cis.repository.CisSwEventTypeDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 服务办事 -- 事件类型管理
 * 
 *  @author Guohw
 */
public class CisSwEventTypeDaoImpl extends BaseDaoImpl
		implements CisSwEventTypeDaoPlus{
	
//	@Override
//	public Page<Map<String, Object>> findTheToDoList(Map<String, SearchFilter> conditions, 
//			Pageable pageable, Integer userId){
//		
//		String sqlString = "   SELECT "
//				+"   	cs.ENROLL_ID AS enrollId,"
//				+"       cs.ENROLL_CODE AS enrollCode,"
//				+"       cs.ENROLL_STATUS AS enrollStatus,"
//				+"       cs.ENROLL_TIME AS enrollTime,"
//				+"       cs.ENROLL_TITLE AS enrollTitle,"
//				+"       cs.REMARK AS remark,"
//				+"       cs.UNITS_ID AS unitsId,"
//				+"       cs.ENROLL_SOURCE AS enrollSource,"
//				+"       cs.ENROLL_PLACE AS enrollPlace,"
//				+"       cs.ENROLL_TYPE AS enrollType,"
//				+"       cs.CLIENT AS client,"
//				+"       cs.CLIENT_PHONE AS clientPhone,"
//				+"       cs.ENROLL_CATEGORY AS enrollCategory,"
//				+"       cs.INVOLE_PEOPLE AS involePeople,"
//				+"       cs.SUGGESTION AS suggestion,"
//				+"       cs.EVALUATE AS evaluate,"
//				+"       cs.EVALUATE_LAST AS evaluateLast,"
//				+"       cs.LON AS lon,"
//				+"       cs.LAT AS lat,"
//				+"       cs.SUBMIT_ID AS submitId,"
//				+"       cs.SIGNFOR_ID AS signFor,"
//				+"       cs.TRANSACT_ID AS transactId,"
//				+"       cs.ESTIMATE_ID AS estimateId"
//				+"     FROM cis_sw_event_enroll cs"
//				+"    WHERE    (    cs.ENROLL_STATUS IN ('appearIn', 'assign', 'signFor')"
//				+"              AND EXISTS"
//				+"                     (SELECT 1"
//				+"                        FROM cis_bm_community_mesh_user cb"
//				+"                       WHERE cb.mesh_id = cs.units_id AND cb.user_id = '" + userId +"'))"
//				+"          OR (    cs.ENROLL_STATUS IN ('transact')"
//				+"              AND EXISTS"
//				+"                     (SELECT 1"
//				+"                        FROM cis_bm_community_mesh_user cb"
//				+"                       WHERE     cb.mesh_id = cs.units_id"
//				+"                             AND cb.IS_MANAGER = 'Y'"
//				+"                             AND cb.user_id = '" + userId + "'))";
//
//		 Page<Map<String, Object>> page =  selectBySqlPageable(sqlString, conditions, pageable);
//		 for(Map<String, Object> map : page.getContent()){
//			 if (!StringUtils.isEmpty(map.get("enrollTime"))) {
//					map.put("enrollTime", map.get("enrollTime")
//							.toString().subSequence(0, 19));
//				}
//		 }
//		 return page;
//	}
	
	@Override
	public Page<Map<String, Object>> findTheToDoList(Map<String, SearchFilter> conditions, 
			Pageable pageable, Integer userId){
		
		String sqlString = "   SELECT"
				+"       enrollId,"
				+"       enrollCode,"
				+"       enrollStatus,"
				+"       enrollTime,"
				+"       enrollTitle,"
				+"       remark,"
				+"       unitsId,"
				+"       enrollSource,"
				+"       enrollPlace,"
				+"       enrollType,"
				+"       client,"
				+"       clientPhone,"
				+"       enrollCategory,"
				+"       suggestion,"
				+"       involePeople,"
				+"       evaluate,"
				+"       evaluateLast,"
				+"       lon,"
				+"       lat,"
				+"       submitId,"
				+"       signFor,"
				+"       transactId,"
				+"       estimateId"
				+"   FROM"
				+"       ("
				+"           SELECT"
				+"               cs.ENROLL_ID AS enrollId,"
				+"               cs.ENROLL_CODE AS enrollCode,"
				+"               cs.ENROLL_STATUS AS enrollStatus,"
				+"               cs.ENROLL_TIME AS enrollTime,"
				+"               cs.ENROLL_TITLE AS enrollTitle,"
				+"               cs.REMARK AS remark,"
				+"               cs.UNITS_ID AS unitsId,"
				+"               cs.ENROLL_SOURCE AS enrollSource,"
				+"               cs.ENROLL_PLACE AS enrollPlace,"
				+"               cs.ENROLL_TYPE AS enrollType,"
				+"               cs.CLIENT AS client,"
				+"               cs.CLIENT_PHONE AS clientPhone,"
				+"               cs.ENROLL_CATEGORY AS enrollCategory,"
				+"               cs.INVOLE_PEOPLE AS involePeople,"
				+"               cs.SUGGESTION AS suggestion,"
				+"               cs.EVALUATE AS evaluate,"
				+"               cs.EVALUATE_LAST AS evaluateLast,"
				+"               cs.LON AS lon,"
				+"               cs.LAT AS lat,"
				+"               cs.SUBMIT_ID AS submitId,"
				+"               cs.SIGNFOR_ID AS signFor,"
				+"               cs.TRANSACT_ID AS transactId,"
				+"               cs.ESTIMATE_ID AS estimateId"
				+"           FROM"
				+"               cis_sw_event_enroll cs"
				+"           WHERE"
				+"               ("
				+"                   cs.ENROLL_STATUS IN("
				+"                       'appearIn',"
				+"                       'assign',"
				+"                       'signFor'"
				+"                   )"
				+"                   AND EXISTS("
				+"                       SELECT"
				+"                           1"
				+"                       FROM"
				+"                           cis_bm_community_mesh_user cb"
				+"                       WHERE"
				+"                           cb.mesh_id = cs.units_id"
				+"                           AND cb.user_id = '" + userId + "'"
				+"                   )"
				+"               )"
				+"               OR("
				+"                   cs.ENROLL_STATUS IN('transact')"
				+"                   AND EXISTS("
				+"                       SELECT"
				+"                           1"
				+"                       FROM"
				+"                           cis_bm_community_mesh_user cb"
				+"                       WHERE"
				+"                           cb.mesh_id = cs.units_id"
				+"                           AND cb.IS_MANAGER = 'Y'"
				+"                           AND cb.user_id = '" + userId + "'"
				+"                   )"
				+"               )"
				+"       ) tcp"
				+"  WHERE 1 = 1";


		 Page<Map<String, Object>> page =  selectBySqlPageable(sqlString, conditions, pageable);
		 for(Map<String, Object> map : page.getContent()){
			 if (!StringUtils.isEmpty(map.get("enrollTime"))) {
					map.put("enrollTime", map.get("enrollTime")
							.toString().subSequence(0, 19));
				}
		 }
		 return page;
	}

}
