package com.nateiot.cis.repository.impl;

import com.nateiot.cis.repository.CisBmHouseMsgDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public class CisBmHouseMsgDaoImpl extends BaseDaoImpl implements CisBmHouseMsgDaoPlus {
	
	@Override
	public  Page<Map<String, Object>> selectHouse(Map<String, SearchFilter> conditions, Pageable pageable){
	
		String sqlString = " SELECT "
					    +" h.ORG AS org, "
					    +" h.HOUSE_ID AS houseId, "
					    +" h.BUILD_ID AS buildId, "
					    +" h.COM_ID AS comId, "
					    +" h.DY_CODE AS dyCode, "
					    +" h.HOUSE_USERNAME AS houseUsername, "
					    +" b.LD_CODE AS ldCode, "
					    +" c.COMMUNITY_NAME AS communityName "
					+" FROM "
					    +" CIS_BM_HOUSE_MSG h  "
					    +" LEFT JOIN cis_bm_building_msg b ON b.BUILD_ID = h.BUILD_ID  "
					    +" LEFT JOIN cis_bm_community_msg c ON c.COM_ID = h.COM_ID "
					+" WHERE "
					    +" 1 = 1 "
					    +" AND not EXISTS( "
					        +" SELECT "
					            +" 1 "
					        +" FROM "
					            +" CIS_BM_HOUSEHOLDER hol "
					        +" WHERE "
					            +" hol.HOUSE_ID = h. HOUSE_ID and hol.DEL_SIGN <> 'Y' "
					    +" ) "
					    +" AND  h.DEL_SIGN <> 'Y' " ;
		
		return selectBySqlPageable(sqlString,conditions, pageable);
	}
	
	/*
	 * 检查 人口信息表中是否与房屋存在关联关系 (non-Javadoc)
	 * @see com.nateiot.cis.repository.CisBmHouseMsgDaoPlus#getExistsHouseForHolder(java.lang.Integer)
	 */
	@Override
	public List<Map<String,Object>> getExistsHouseForHolder(Integer houseId){
		
		String sqlString = " SELECT "
						    +" count(1) AS counts  "
						    +" FROM "
						        +" cis_bm_house_msg h "
						    +" WHERE "
						        +" EXISTS( "
						            +" SELECT "
						                +" 1 "
						            +" FROM "
						                +" cis_bm_householder hou "
						            +" WHERE "
						                +" hou.DEL_SIGN <> 'Y' "
						                +" AND hou.HOUSE_ID = h.HOUSE_ID "
						        +" ) and h.HOUSE_ID="+houseId + "  limit 1  ";
		return this.selectBySql(sqlString);
	}
	
	/**
	 * 获取房屋信息，可以根据人口信息中的姓名查询房屋数据
	 */
	public  Page<Map<String, Object>>  queryHouseByHolder(Map<String, SearchFilter> conditions, Pageable pageable){
		
		ResultFields fields = new ResultFields();
		fields.addField("houseId")
		.addField("org")
		.addField("buildId")
		.addField("comId")
		.addField("dyCode")
		.addField("houseUsername")
		.addField("lat")
		.addField("lon")
		.addField("communityName")
		.addField("ldCode")
		.addField("householderId")
		.addField("householderName")
		.addField("cardCode");
		
		String sqlString = " SELECT "
							+ "house.HOUSE_ID as houseId, "
							+ "house.ORG as org, "
							+ "house.BUILD_ID as buildId, "
							+ "house.COM_ID as comId, "
							+ "house.DY_CODE as dyCode, "
							+ "house.HOUSE_USERNAME as houseUsername, "
							+ "house.LAT as lat, "
							+ "house.LON as lon, "
							+ "c.COMMUNITY_NAME as communityName, "
							+ "b.LD_CODE as ldCode, "
							+ "h.HOUSEHOLDER_ID as householderId, "
							+ "h.HOUSEHOLDER_NAME as householderName, "
							+ "h.CARD_CODE as  cardCode "
						+ " FROM "
						     + " cis_bm_house_msg house  "
						     + " left JOIN cis_bm_householder h  ON h.HOUSE_ID = house.HOUSE_ID  "
							 + " left join cis_bm_community_msg c on  c.COM_ID=house.COM_ID "
							 + " left join  cis_bm_building_msg b on b.BUILD_ID=house.BUILD_ID "
							 +" where house.DEL_SIGN <> 'Y'  ";
		
		return this.selectBySqlPageable(sqlString,conditions," house.HOUSE_ID " , pageable,fields);
	}

	/**
	 * 获取房屋信息，可以根据人口信息中的姓名查询房屋数据
	 */
	public  Page<Map<String, Object>>  queryHouseByHolderLikeArgs(Map<String, SearchFilter> conditions, Pageable pageable,String args,String meshId){

		String qstr = "";

		if(StringUtils.isNotBlank(args)){
			 qstr = qstr +  " and  ( house.DY_CODE like '%"+args+"%' or b.LD_CODE  like '%"+args+"%' or c.COMMUNITY_NAME like '%"+args+"%' or h.HOUSEHOLDER_NAME like '%"+args+"%'  )";
		}

		if(StringUtils.isNotBlank(meshId)){
			qstr = qstr +  " and  house.ORG in ("+meshId+") ";
		}

		ResultFields fields = new ResultFields();

		fields.addField("houseId")
				.addField("org")
				.addField("buildId")
				.addField("comId")
				.addField("dyCode")
				.addField("houseUsername")
				.addField("lat")
				.addField("lon")
				.addField("communityName")
				.addField("ldCode")
				.addField("householderId")
				.addField("householderName")
				.addField("cardCode")
				.addField("callPhone");

		String sqlString = " SELECT "
				+ "house.HOUSE_ID as houseId, "
				+ "house.ORG as org, "
				+ "house.BUILD_ID as buildId, "
				+ "house.COM_ID as comId, "
				+ "house.DY_CODE as dyCode, "
				+ "house.HOUSE_USERNAME as houseUsername, "
				+ "house.LAT as lat, "
				+ "house.LON as lon, "
				+ "c.COMMUNITY_NAME as communityName, "
				+ "b.LD_CODE as ldCode, "
				+ "h.HOUSEHOLDER_ID as householderId, "
				+ "h.HOUSEHOLDER_NAME as householderName, "
				+ "h.CARD_CODE as  cardCode ,"
				+ "h.CALL_PHONE AS callPhone "
				+ " FROM "
				+ " cis_bm_house_msg house  "
				+ " left JOIN cis_bm_householder h  ON h.HOUSE_ID = house.HOUSE_ID  "
				+ " left join cis_bm_community_msg c on  c.COM_ID=house.COM_ID "
				+ " left join  cis_bm_building_msg b on b.BUILD_ID=house.BUILD_ID "
				+" where house.DEL_SIGN <> 'Y' and house.LAT IS NOT NULL and house.LON IS NOT NULL and LENGTH(house.LAT) > 0  and  LENGTH(house.LON) >0 " + qstr;

		return this.selectBySqlPageable(sqlString ," house.HOUSE_ID " , pageable, fields);
	}
	
}
