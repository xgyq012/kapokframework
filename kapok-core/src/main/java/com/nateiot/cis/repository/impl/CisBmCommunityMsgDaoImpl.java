package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.repository.CisBmCommunityMsgDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;

public class  CisBmCommunityMsgDaoImpl extends BaseDaoImpl implements  CisBmCommunityMsgDaoPlus {

	
	/*
	 * 获取和小区信息存在关系的个数(non-Javadoc)
	 * @see com.nateiot.cis.repository.CisBmCommunityMsgDaoPlus#getExistsCommunityForBuildORHouse(java.lang.Integer)
	 */
	@Override
	public List<Map<String,Object>> getExistsCommunityForBuildORHouse(Integer comId){
		String sqlString = " SELECT "
				    +" COUNT(1) as counts "
				    +" FROM "
				        +" cis_bm_community_msg c "
				    +" WHERE "
				        +"( EXISTS( "
				            +" SELECT "
				                +" 1 "
				            +" FROM "
				                +" cis_bm_building_msg b "
				            +" WHERE "
				                +" b.COM_ID = c.COM_ID "
				                +" AND b.DEL_SIGN <> 'Y' "
				        +" ) "
				        +" OR EXISTS( "
				            +" SELECT "
				                +" 1 "
				            +" FROM "
				                +" cis_bm_house_msg h "
				            +" WHERE "
				                +" h.COM_ID = c.COM_ID "
				                +" AND h.DEL_SIGN <> 'Y' "
				        +" ) ) AND c.COM_ID = " + comId;
		
		return this.selectBySql(sqlString);
	}
	
}
