package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisAdAuxiliaryDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 辅助决策 
 * 
 * @author Administrator
 *
 */
public class CisAdAuxiliaryDaoImpl extends BaseDaoImpl implements
        CisAdAuxiliaryDaoPlus {
	
	
	/**
	 * 人口信息统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> rkxxtj( Map<String, SearchFilter> conditions,Pageable pageable){
		
		ResultFields fields = new ResultFields();
		fields.addField("orgId")
		.addField("fullPath")
		.addField("rkzs")
		.addField("czrk")
		.addField("ldrk")
		.addField("kgrk")
		.addField("dy")
		.addField("ssmz")
		.addField("lnr")
		.addField("lset");
		
		String sqlString = "select "
					+" h.ORG_ID as orgId, "
					+" mh.FULL_PATH as fullPath , "
					+" count(h.HOUSEHOLDER_ID) as 'rkzs', "
					+" sum(case h.gllx when '4' then 1 else 0 end) as 'czrk', "
					+" sum(case when h.gllx='2' or h.gllx = '3' then 1 else 0 end) as 'ldrk', "
					+" sum(case h.gllx when '1' then 1 else 0 end) as 'kgrk', "
					+" sum(case h.POLITICS_STATUS when '3' then 1 else 0 end) as 'dy', "
					+" sum(case when h.NATIONALITY!='hanzu' AND h.NATIONALITY is not null then 1 else 0 end) as 'ssmz', "
					+" sum((select count(1) from CIS_BM_OLD_PEOPLE_H p where p.HOUSEHOLDER_ID=h.HOUSEHOLDER_ID)) as 'lnr', "
					+" sum((select count(1) from cis_bm_leftover_children c where c.HOUSEHOLDER_ID=h.HOUSEHOLDER_ID)) as 'lset' "
				    +" from cis_bm_householder h left join cis_bm_community_mesh mh on h.ORG_ID = mh.MESH_ID "
				    + " where h.DEL_SIGN = 'N'  " ;
		
		return this.selectBySqlPageable(sqlString, conditions , "  h.ORG_ID " , pageable ,fields ) ;
	
	}
	
	/**
	 * 房屋管理信息统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> fwgltj(Map<String, SearchFilter> conditions, Pageable pageable) {
		ResultFields fields = new ResultFields();
		fields.addField("orgId")
		.addField("fullPath")
		.addField("building")
		.addField("community")
		.addField("house");
		String sqlString =  " select  "
							+" t.org as orgId, "
							+" mh.FULL_PATH as fullPath, "
							+" sum(case when t.A='build' then t.num else 0 end) as building, "
							+" sum(case when t.A='community' then t.num else 0 end) as community, "
							+" sum(case when t.A='house' then t.num else 0 end) as house "
							+" from ( "
							+" select c.org as org ,count(1) as num , 'community' as 'A' from cis_bm_community_msg c where c.DEL_SIGN <> 'Y' group  by c.org  "
							+" UNION ALL "
							+" select b.org as org ,count(1) as num ,'build' as 'A' from cis_bm_building_msg b where b.DEL_SIGN <> 'Y' group by b.org "
							+" UNION ALL "
							+" select h.org as org ,count(1) as num ,'house' as 'A' from cis_bm_house_msg h where h.DEL_SIGN <> 'Y' group by h.org  "
							+" )t  left join cis_bm_community_mesh mh on t.org = mh.MESH_ID  "
							+" where 1=1  " ;
		return this.selectBySqlPageable(sqlString, conditions , "  t.org " , pageable ,fields ) ;
	}
	 
	 
	/**
	 * 综合治理数据统计(查询) 
	 * 
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> overHaulFormSearch(
			Map<String, SearchFilter> conditions) {
		String sqlString = ""
						+ "   SELECT"
						+ "    orgId,"
						+ "    orgName,"
						+ "    orgFullName,"
						+ "    judicalNum,"
						+ "    petotionNum,"
						+ "    patrolNum,"
						+ "    duryroowNum,"
						+ "    caseNum"
						+ "   FROM("
						+ "     SELECT"
						+ "      a.COMMUNITYORG_ID AS orgId,"
						+ "      a.COMMUNITYORG_NAME AS orgName,"
						+ "      a.COMMUNITYORG_FULLNAME AS orgFullName,"
						+ "      SUM(b.JUDICIAL_ID) AS judicalNum,"
						+ "      SUM(c.PETITION_ID) AS petotionNum,"
						+ "      SUM(d.PATROL_ID) AS patrolNum,"
						+ "      SUM(e.DURYROOM_ID) AS duryroowNum,"
						+ "      SUM(f.CASE_ID) AS caseNum"
						+ "     FROM"
						+ "      cis_bm_community_org a LEFT JOIN cis_bm_judicial_info b"
						+ "      ON b.INSTITUTIONS = a.COMMUNITYORG_ID LEFT JOIN cis_bm_petition_info c"
						+ "      ON c.INSTITUTIONS = a.COMMUNITYORG_ID LEFT JOIN cis_bm_patrol_info d"
						+ "      ON d.INSTITUTIONS = a.COMMUNITYORG_ID LEFT JOIN cis_bm_dury_room_info e"
						+ "      ON e.INSTITUTIONS = a.COMMUNITYORG_ID LEFT JOIN cis_bm_case_info f"
						+ "      ON f.INSTITUTIONS = a.COMMUNITYORG_ID"
						+ "    GROUP BY"
						+ "     a.COMMUNITYORG_ID"
						+ "    ORDER BY"
						+ "     a.COMMUNITYORG_ID"
						+ "    ) tt";
				
		return selectBySql(sqlString);
	}

	/**
	 * 人员事件处理统计
	 */
	@Override
	public Page<Map<String, Object>> event(
		Map<String, SearchFilter> conditions, Pageable pageable) {
		ResultFields fields = new ResultFields();
		fields.addField("orgId")
		.addField("fullPath")
		.addField("number") 
		.addField("submitSum")
		.addField("assign")
		.addField("sign")
		.addField("dispose")
		.addField("evaluate");
		String sqlString = "select "
				+ " r.BACKLOG_ID as orgId, "
				+ " mh.FULL_PATH as fullPath , "
				+ " sum((case when r.PROCESS='appearIn' then 1 else 0 end)+(case when r.PROCESS='signFor' then 1 else 0 end)"
				+ " +(case when r.PROCESS='transact' then 1 else 0 end)+(case when r.PROCESS='estimate' then 1 else 0 end)+(case when r.PROCESS='assign' then 1 else 0 end)) as 'number',"
				+ " sum(case when r.PROCESS='appearIn' then 1 else 0 end) as 'submitSum', "
				+ " sum(case when r.PROCESS='assign' then 1 else 0 end) as 'assign', "
				+ " sum(case when r.PROCESS='signFor' then 1 else 0 end) as 'sign', "
				+ " sum(case when r.PROCESS='transact' then 1 else 0 end) as 'dispose', "
				+ " sum(case when r.PROCESS='estimate' then 1 else 0 end) as 'evaluate'"
			    + " from cis_sw_event_enroll e JOIN cis_sw_enroll_rowtable r ON r.ENROLL_ID = e.ENROLL_ID left join cis_bm_community_mesh mh on r.BACKLOG_ID = mh.MESH_ID "
			    + " where 1=1";
		
		return this.selectBySqlPageable(sqlString, conditions , " r.BACKLOG_ID " , pageable ,fields) ;
	}

	/**
	 * 党群建设统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> party(Map<String, SearchFilter> conditions, Pageable pageable) {
		ResultFields fields = new ResultFields();
		fields.addField("orgId")
		.addField("fullPath")
		//.addField("sum")
		.addField("organization")
		.addField("partyMember")
		.addField("aborUnion")
		.addField("member")
		.addField("nspection")
		.addField("womanCadres");
		String sqlString =  " select  "
							+" t.org as orgId, "
							+" mh.FULL_PATH as fullPath, "
							+" sum(case when t.A='organization' then t.num else 0 end) as organization, "
							+" sum(case when t.A='partyMember' then t.num else 0 end) as partyMember, "
							+" sum(case when t.A='aborUnion' then t.num else 0 end) as aborUnion, "
							+" sum(case when t.A='member' then t.num else 0 end) as member, "
							+" sum(case when t.A='nspection' then t.num else 0 end) as nspection, "
							+" sum(case when t.A='womanCadres' then t.num else 0 end) as womanCadres "
							+" from ( "
							+" select o.org as org ,count(1) as num , 'organization' as 'A' from cis_bm_party_organization o group  by o.org  "
							+" UNION ALL "
							+" select ho.ORG_ID as org ,count(1) as num ,'partyMember' as 'A' from cis_bm_householder ho where ho.POLITICS_STATUS ='3' group by ho.ORG_ID "
							+" UNION ALL "
							+" select g.org as org ,count(1) as num ,'aborUnion' as 'A' from cis_bm_guild_organ_info g group by g.org  "
							+" UNION ALL "
							+" select h.org_Id as org ,count(1) as num ,'member' as 'A' from cis_bm_householder h where h.DEL_SIGN = 'N' and h.POLITICS_STATUS = '2' group by h.org_Id "
							+" UNION ALL "
							+" select i.org as org ,count(1) as num ,'nspection' as 'A' from cis_bm_inspect_msg i group by i.org  "
							+" UNION ALL "
							+" select w.org as org ,count(1) as num ,'womanCadres' as 'A' from cis_bm_woman_msg w group by w.org  "
							+" )t  left join cis_bm_community_mesh mh on mh.MESH_ID = t.org  "
							+" where 1=1  " ;
		return this.selectBySqlPageable(sqlString, conditions , "  t.org " , pageable ,fields ) ;
	}

	/**
	 * 应急管理统计
	 */
	@Override
	public Page<Map<String, Object>> emergency(
		Map<String, SearchFilter> conditions, Pageable pageable) {
		ResultFields fields = new ResultFields();
		fields.addField("yjsjlxId")
		.addField("fullPath")
		.addField("name")
		.addField("member")
		.addField("draft")
		.addField("isSubmit")
		.addField("inspect")
		.addField("nvestigate")
		.addField("followUp");
		
		String sqlString = "select "
					+" h.YJSJLX_ID as yjsjlxId, "
					+" mh.FULL_PATH as fullPath , "
					+" mh.LEIXING_NAME as name , "
					+" sum((case h.SHIJIAN_STATUS when '0' then 1 else 0 end)+(case h.SHIJIAN_STATUS when '1' then 1 else 0 end)+(case h.SHIJIAN_STATUS when '2' then 1 else 0 end)+(case h.SHIJIAN_STATUS when '3' then 1 else 0 end)+(case h.SHIJIAN_STATUS when '4' then 1 else 0 end)) as member, "
					+" sum(case h.SHIJIAN_STATUS when '0' then 1 else 0 end) as 'draft', "
					+" sum(case h.SHIJIAN_STATUS when '1' then 1 else 0 end) as 'isSubmit', "
					+" sum(case h.SHIJIAN_STATUS when '2' then 1 else 0 end) as 'inspect', "
					+" sum(case h.SHIJIAN_STATUS when '3' then 1 else 0 end) as 'nvestigate', "
					+" sum(case h.SHIJIAN_STATUS when '4' then 1 else 0 end) as 'followUp'"
				    +" from cis_em_yingji_shijian h left join cis_em_yjsjlx mh on h.YJSJLX_ID = mh.YJSJLX_ID"
					+ " where 1=1 " 
					+ " and mh.DEL_SIGN = 'N'  " ;
		return this.selectBySqlPageable(sqlString, conditions ," h.YJSJLX_ID", pageable ,fields ) ;
	}

	/**
	 * 机构人员统计
	 */
	@Override
	public Page<Map<String, Object>> orgPeople(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		ResultFields fields = new ResultFields();
		fields.addField("orgId")
		.addField("fullPath")
		.addField("member");
		String sqlString =  "SELECT"
				+ " m.MESH_ID AS orgId ,"
				+ " me.FULL_PATH AS fullPath ,"
				+ " COUNT(DISTINCT u.USER_NAME) as member"
				+ " FROM  "
				+ " gxwl_sys_user u JOIN cis_bm_community_mesh_user m ON m.USER_ID = u.USER_ID"
				+ " left JOIN cis_bm_community_mesh me ON me.MESH_ID = m.MESH_ID"
				+ " WHERE 1 = 1 ";
		return this.selectBySqlPageable(sqlString, conditions , "  m.MESH_ID " , pageable ,fields ) ;
	}

	/**
	 * 特殊人群统计
	 */
	@Override
	public Page<Map<String, Object>> crowd(
		Map<String, SearchFilter> conditions, Pageable pageable) {
		ResultFields fields = new ResultFields();
		fields.addField("orgId")
		.addField("fullPath")
		.addField("sum")
		.addField("children")
		.addField("teenager")
		.addField("heresy")
		.addField("punishment")
		.addField("drugs")
		.addField("rectify");
		String sqlString =  " select  "
							+" t.org as orgId, "
							+" mh.FULL_PATH as fullPath, "
							+" sum((case when t.A='children' then t.num else 0 end)+(case when t.A='teenager' then t.num else 0 end)"
							+" +(case when t.A='heresy' then t.num else 0 end)+(case when t.A='teenager' then t.num else 0 end)"
							+" +(case when t.A='drugs' then t.num else 0 end)+(case when t.A='rectify' then t.num else 0 end)) as sum ," 
							+" sum(case when t.A='children' then t.num else 0 end) as children, "
							+" sum(case when t.A='teenager' then t.num else 0 end) as teenager, "
							+" sum(case when t.A='heresy' then t.num else 0 end) as heresy ,"
							+" sum(case when t.A='punishment' then t.num else 0 end) as punishment, "
							+" sum(case when t.A='drugs' then t.num else 0 end) as drugs, "
							+" sum(case when t.A='rectify' then t.num else 0 end) as rectify "
							+" from ( "
							+" select h.ORG_ID as org ,count(1) as num , 'children' as 'A' from cis_bm_leftover_children c join cis_bm_householder h on h.HOUSEHOLDER_ID = c.HOUSEHOLDER_ID group  by h.ORG_ID  "
							+" UNION ALL "
							+" select h1.ORG_ID as org ,count(1) as num ,'teenager' as 'A' from cis_bm_wfjl w join cis_bm_householder h1 on h1.HOUSEHOLDER_ID = w.HOUSEHOLDER_ID group by h1.ORG_ID "
							+" UNION ALL "
							+" select h2.ORG_ID as org ,count(1) as num ,'heresy' as 'A' from cis_bm_csxjry cs join cis_bm_householder h2 on h2.HOUSEHOLDER_ID = cs.HOUSEHOLDER_ID group by h2.ORG_ID "
							+" UNION ALL "
							+" select h3.ORG_ID as org ,count(1) as num , 'punishment' as 'A' from cis_bm_fxry f join cis_bm_householder h3 on h3.HOUSEHOLDER_ID = f.HOUSEHOLDER_ID group by h3.ORG_ID "
							+" UNION ALL "
							+" select h4.ORG_ID as org ,count(1) as num ,'drugs' as 'A' from cis_bm_xdry x join cis_bm_householder h4 on h4.HOUSEHOLDER_ID = x.HOUSEHOLDER_ID group by h4.ORG_ID "
							+" UNION ALL "
							+" select h5.ORG_ID as org ,count(1) as num ,'rectify' as 'A' from cis_bm_sqjzdx s left join cis_bm_householder h5 on h5.HOUSEHOLDER_ID = s.HOUSEHOLDER_ID group by h5.ORG_ID "
							+" )t  left join cis_bm_community_mesh mh on t.org = mh.MESH_ID  "
							+" where 1=1  " ;
		return this.selectBySqlPageable(sqlString, conditions , "  t.org " , pageable ,fields ) ;
	}

	/**
	 * 综合治理统计
	 */
	@Override
	public Page<Map<String, Object>> govern(
		Map<String, SearchFilter> conditions, Pageable pageable) {
			ResultFields fields = new ResultFields();
			fields.addField("orgId")
			.addField("fullPath")
			.addField("judicial")
			.addField("petition")
			.addField("details")
			.addField("patrol")
			.addField("technical")
			.addField("duty");
			String sqlString =  " select  "
								+" t.org as orgId, "
								+" mh.FULL_PATH as fullPath, "
								+" sum(case when t.A='judicial' then t.num else 0 end) as judicial, "
								+" sum(case when t.A='petition' then t.num else 0 end) as petition ,"
								+" sum(case when t.A='details' then t.num else 0 end) as details, "
								+" sum(case when t.A='patrol' then t.num else 0 end) as patrol, "
								+" sum(case when t.A='technical' then t.num else 0 end) as technical ,"
								+" sum(case when t.A='duty' then t.num else 0 end) as duty "
								+" from ( "
								+" select j.ORG_ID as org ,count(1) as num , 'judicial' as 'A' from CIS_BM_JUDICIAL_INFO j group by j.ORG_ID  "
								+" UNION ALL "
								+" select p.ORG_ID as org ,count(1) as num ,'petition' as 'A' from CIS_BM_PETITION_INFO p group by p.ORG_ID "
								+" UNION ALL "
								+" select c.ORG_ID as org ,count(1) as num ,'details' as 'A' from CIS_BM_CASE_INFO c group by c.ORG_ID "
								+" UNION ALL "
								+" select i.ORG_ID as org ,count(1) as num , 'patrol' as 'A' from CIS_BM_PATROL_INFO i group by i.ORG_ID "
								+" UNION ALL "
								+" select s.ORG_ID as org ,count(1) as num ,'technical' as 'A' from CIS_BM_SAFETY_INFO s group by s.ORG_ID "
								+" UNION ALL "
								+" select d.ORG_ID as org ,count(1) as num ,'duty' as 'A' from CIS_BM_DURY_ROOM_INFO d group by d.ORG_ID "
								+" )t  left join cis_bm_community_mesh mh on t.org = mh.MESH_ID  "
								+" where 1=1  " ;
			return this.selectBySqlPageable(sqlString, conditions , "  t.org " , pageable ,fields ) ;
	}
	
}
