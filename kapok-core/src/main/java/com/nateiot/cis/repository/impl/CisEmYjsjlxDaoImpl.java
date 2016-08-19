package com.nateiot.cis.repository.impl;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.repository.CisEmYjsjlxDaoPlus;
import com.nateiot.core.repository.impl.BaseDaoImpl;

/**
 * 应急事件类型
 * @author xiewenhua
 *
 */
public class CisEmYjsjlxDaoImpl extends BaseDaoImpl implements CisEmYjsjlxDaoPlus{

	@Override
	public List<Map<String, Object>> getEmergency() {
		String sqlString = ""
				+ " SELECT"
				+ "     DISTINCT c.YJSJLX_ID AS yjsjlxId"
				+ "     ,c.LEIXING_NAME AS leixingName"
				+ "     ,c.LEIXING_PS AS leixingPs"
				+ "     ,c.COLOR AS color"
				+ "     ,c.FULL_NAME AS fullName"
				+ "     ,c.FULL_PATH AS fullPath"
				+ "     ,c.PARENT_ID AS parentId"
				+ " FROM"
				+ "     cis_em_yjsjlx c,cis_em_yjsjlx y,cis_em_yingji_shijian e"
				+ " WHERE"
				+ "     c.full_path LIKE CONCAT( y.full_path, '%' )"
				//+ "     AND y.YJSJLX_ID = e.YJSJLX_ID"
				+ "     AND c.DEL_SIGN = 'N'";
			
		return selectBySql(sqlString);
	}

}
