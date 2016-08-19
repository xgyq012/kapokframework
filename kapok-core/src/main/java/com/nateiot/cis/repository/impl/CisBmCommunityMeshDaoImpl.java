package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.repository.CisBmCommunityMeshDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * @author zhangweiming
 *
 */
public class CisBmCommunityMeshDaoImpl extends BaseDaoImpl implements CisBmCommunityMeshDaoPlus {

	@Override
	public List<Map<String, Object>> getUserAllMesh(Integer userId) {
		String sqlString = ""
						+ " SELECT"
						+ "     DISTINCT c.mesh_id AS meshId"
						+ "     ,c.mesh_code AS meshCode"
						+ "     ,c.mesh_name AS meshName"
						+ "     ,c.mesh_full_name AS meshFullName"
						+ "     ,c.mesh_type AS meshType"
						+ "     ,c.mesh_desc AS meshDesc"
						+ "     ,c.suo_fang_ji_bie AS suoFangJiBie"
						+ "     ,c.fan_wei_zuo_biao AS fanWeiZuoBiao"
						+ "     ,c.lon AS lon"
						+ "     ,c.lat AS lat"
						+ "     ,c.parent_mesh_id AS parentMeshId"
						+ "     ,c.full_path fullPath"
						+ "     ,c.is_leaf isLeaf"
						+ " FROM"
						+ "     cis_bm_community_mesh p"
						+ "     ,cis_bm_community_mesh c"
						+ "     ,cis_bm_community_mesh_user u"
						+ " WHERE"
						+ "     c.del_sign = 'N'"
						+ "     AND c.full_path LIKE CONCAT( p.full_path, '%' )"
						+ "     AND p.mesh_id = u.mesh_id"
						+ "     AND u.user_id = " + userId;
		return selectBySql(sqlString);
	}
	
	@Override
	public List<Map<String, Object>> getMeshChildren(Integer meshId) {
		String sqlString = ""
						+ " SELECT"
						+ "     c.mesh_id AS meshId"
						+ "     ,c.mesh_code AS meshCode"
						+ "     ,c.mesh_name AS meshName"
						+ "     ,c.mesh_full_name AS meshFullName"
						+ "     ,c.mesh_type AS meshType"
						+ "     ,c.mesh_desc AS meshDesc"
						+ "     ,c.suo_fang_ji_bie AS suoFangJiBie"
						+ "     ,c.fan_wei_zuo_biao AS fanWeiZuoBiao"
						+ "     ,c.lon AS lon"
						+ "     ,c.lat AS lat"
						+ "     ,c.parent_mesh_id AS parentMeshId"
						+ "     ,c.full_path fullPath"
						+ "     ,c.is_leaf isLeaf"
						+ " FROM"
						+ "     cis_bm_community_mesh p"
						+ "     ,cis_bm_community_mesh c"
						+ " WHERE"
						+ "     c.del_sign = 'N'"
						+ "     AND c.full_path LIKE CONCAT( p.full_path, '%' )"
						+ "     AND p.mesh_id = " + meshId;
		return selectBySql(sqlString);
	}

	@Override
	public Map<String, Object> count(String meshIds) {
		ResultFields fields = new ResultFields();
		fields
		.addField("ldzs")    //楼栋总数		
		.addField("fwzs")    //房屋总数	
		.addField("mdzs")    //门店总数
		.addField("xxzs")    //学校总数
		.addField("yyzs")    //医院总数
		.addField("xqzs")//小区总数
		.addField("sfsjzs") //司法事件总数
		.addField("afzs")   //案发总数
		.addField("xfdzs")  //巡防队总数
		.addField("zbszs")  //值班室总数
		.addField("sxtzs"); //摄像头总数
		
		String sqlString = "SELECT" 
				+" SUM((SELECT COUNT(*) FROM cis_bm_building_msg bbm WHERE bbm.ORG = cm.MESH_ID)) AS ldzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_house_msg hm WHERE hm.ORG = cm.MESH_ID)) AS fwzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_shop s WHERE s.SSJG = cm.MESH_ID)) AS mdzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_school bs WHERE bs.SSJG = cm.MESH_ID)) AS xxzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_hospital bh WHERE bh.SSJG = cm.MESH_ID)) AS yyzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_community_msg msg WHERE msg.ORG = cm.MESH_ID)) AS xqzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_judicial_info ji WHERE ji.ORG_ID = cm.MESH_ID)) AS sfsjzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_case_info ci WHERE ci.ORG_ID = cm.MESH_ID)) AS afzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_patrol_info li WHERE li.ORG_ID = cm.MESH_ID)) AS xfdzs, "
				+" SUM((SELECT COUNT(*) FROM cis_bm_dury_room_info dri WHERE dri.ORG_ID = cm.MESH_ID)) AS zbszs, "
				+" SUM((SELECT SUM(si.CAMER_NUM) FROM cis_bm_safety_info si WHERE si.ORG_ID = cm.MESH_ID)) AS sxtzs "
				+" FROM cis_bm_community_mesh cm "
				+" WHERE (cm.DEL_SIGN is null or cm.DEL_SIGN = 'N') AND cm.MESH_ID IN ("+ meshIds + ")";
		return this.selectOneBySql(sqlString, fields);
	}
	
}
