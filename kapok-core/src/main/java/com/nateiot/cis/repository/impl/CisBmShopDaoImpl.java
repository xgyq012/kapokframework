package com.nateiot.cis.repository.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.repository.CisBmShopDaoPlus;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.BaseDaoImpl;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * 门店信息
 * 
 * @author Administrator
 *
 */
public class CisBmShopDaoImpl extends BaseDaoImpl implements
		CisBmShopDaoPlus {

	@Override
	public Page<Map<String, Object>> queryShop(
		Map<String, SearchFilter> conditions, Pageable pageable) {
		ResultFields fields = new ResultFields();
		fields.addField("shopId")
		.addField("org")
		.addField("mdmc")
		.addField("mddz")
		.addField("lon")
		.addField("lat");
		String sqlString = " SELECT "
							+ " s.SHOP_ID as shopId, "
							+ " s.SSJG as org, "
							+ " s.MDMC as mdmc, "
							+ " s.MDDZ as mddz, "
							+ " s.LON as lon, "
							+ " s.LAT as lat "
							+ " FROM "
						    + " cis_bm_shop s  "
							+" where 1=1 ";
		
		return this.selectBySqlPageable(sqlString,conditions," s.SHOP_ID " , pageable,fields);
	}

}
