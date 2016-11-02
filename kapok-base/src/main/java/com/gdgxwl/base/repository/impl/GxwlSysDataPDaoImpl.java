package com.gdgxwl.base.repository.impl;

import com.gdgxwl.base.repository.GxwlSysDataPDaoPlus;
import com.gdgxwl.core.repository.impl.BaseDaoImpl;
import com.gdgxwl.core.repository.impl.ResultFields;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public class GxwlSysDataPDaoImpl extends BaseDaoImpl implements
		GxwlSysDataPDaoPlus {

	@Override
	public List<Map<String, Object>> getCurrentUserDataByResourceId(Integer userId,
			Integer resourceId) {
		ResultFields resultFields = new ResultFields();
		resultFields.addField("datapId");
		resultFields.addField("resourceId");
		resultFields.addField("datapType");
		resultFields.addField("columnNames");
		resultFields.addField("titleNames");
		resultFields.addField("sqlWhere");
		resultFields.addField("noControls");
		resultFields.addField("enable");
		
		String sqlString = ""
						+ " SELECT"
						+ "     DISTINCT d.datap_id"
						+ "     ,d.resource_id"
						+ "     ,d.datap_type"
						+ "     ,d.column_names"
						+ "     ,d.title_names"
						+ "     ,d.sql_where"
						+ "     ,d.no_controls"
						+ "     ,d.enable"
						+ " FROM"
						+ "     gxwl_sys_roleuser ru"
						+ "     ,gxwl_sys_roleredatap rd"
						+ "     ,gxwl_sys_datap d"
						+ " WHERE"
						+ "     d.datap_id = rd.datap_id"
						+ "     AND rd.role_id = ru.role_id"
						+ "     AND d.enable = 'Y'"
						+ "     AND rd.enable = 'Y'"
						+ "     AND d.resource_id = " + resourceId
						+ "     AND ru.user_id = " + userId;
		return selectBySql(sqlString, resultFields);
	}

}
