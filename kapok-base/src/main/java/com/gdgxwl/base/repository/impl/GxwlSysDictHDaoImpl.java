package com.gdgxwl.base.repository.impl;

import com.gdgxwl.base.repository.GxwlSysDictHDaoPlus;
import com.gdgxwl.core.repository.impl.BaseDaoImpl;
import com.gdgxwl.core.repository.impl.ResultFields;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public class GxwlSysDictHDaoImpl extends BaseDaoImpl implements
		GxwlSysDictHDaoPlus {

	@Override
	public List<Map<String, Object>> getDictByDictTypeCode(String dictTypeCode) {
		
		ResultFields resultFields = new ResultFields();
		resultFields.addField("dictTypeCode")
		.addField("dictType")
		.addField("dictName")
		.addField("seq")
		.addField("isDisplay")
		.addField("lastUpdateTime");
		
		String sqlString = "SELECT"
								+ " h.dict_type_code"
								+ " ,l.dict_code"
								+ " ,l.dict_name"
								+ " ,l.seq"
								+ " ,l.is_display"
								+ " ,l.last_update_time"
							+ " FROM"
								+ " gxwl_sys_dict_h h"
								+ " ,gxwl_sys_dict_l l"
							+ " WHERE"
								+ " l.dict_type_id = h.dict_type_id"
//								+ " AND l.is_display = 'Y'"
								+ " AND h.dict_type_code in (" + dictTypeCode + ")"
//								+ " AND h.enable = 'Y'"
							+ " ORDER BY"
								+ " l.last_update_time DESC";
		
		return selectBySql(sqlString, resultFields);
	}

	@Override
	public List<Map<String, Object>> getDictTypeList() {
		String sqlString = "SELECT"
								+ " h.dict_type_id as dictTypeId"
								+ " ,h.dict_type_name as dictTypeName"
								+ " ,h.dict_type_code as dictTypeCode"
								+ " ,h.enable as enable"
							+ " FROM"
								+ " gxwl_sys_dict_h h"
							+ " WHERE"
								+ " h.enable = 'Y'";

		return selectBySql(sqlString);
	}

	@Override
	public List<Map<String, Object>> getDictList() {
		String sqlString = "SELECT"
							+ " l.dict_id as dictId"
							+ " ,l.dict_type_id as dictTypeId"
							+ " ,h.dict_type_code as dictTypeCode"
							+ " ,l.dict_name as dictName"
							+ " ,l.dict_code as dictCode"
							+ " ,l.seq as seq"
							+ " ,l.is_display as isDisplay"
						+ " FROM"
							+ " gxwl_sys_dict_l l"
						+ " LEFT JOIN"
							+ " gxwl_sys_dict_h h"
						+ " ON"
							+ " l.dict_type_id = h.dict_type_id"
						+ " WHERE"
							+ " l.is_display = 'Y'"
							+ " AND h.enable = 'Y'";

		return selectBySql(sqlString);
	}

}
