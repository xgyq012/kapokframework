package com.gdgxwl.base.repository.impl;

import com.gdgxwl.base.repository.GxwlSysRoleDaoPlus;
import com.gdgxwl.core.repository.impl.BaseDaoImpl;
import com.gdgxwl.core.repository.impl.Field.DataType;
import com.gdgxwl.core.repository.impl.ResultFields;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public class GxwlSysRoleDaoImpl extends BaseDaoImpl implements
		GxwlSysRoleDaoPlus {

	@Override
	public List<Map<String, Object>> getUsersByRoleId(Integer roleId) {
		String sqlString = "SELECT "
								+ "b.realname as realname "
							+ "FROM "
								+ "gxwl_sys_roleuser a, "
								+ "gxwl_sys_user b "
							+ "where "
								+ "b.user_id = a.user_id and "
								+ "a.role_id = " + roleId;
		return selectBySql(sqlString);
	}

	@Override
	public List<Map<String, Object>> getGxwlSysResourceByRoleId(Integer roleId) {
		ResultFields fields = new ResultFields();
		fields.addField("relaId")
		.addField("roleId")
		.addField("createrId")
		.addField("createTime", DataType.Timestamp)
		.addField("resourceId")
		.addField("resourceCode")
		.addField("resourceName")
		.addField("resourceLabel")
		.addField("parentResourceId")
		.addField("resourceType")
		.addField("seq")
		.addField("url")
		.addField("imagePath")
		.addField("openMode")
		.addField("fullpath")
		.addField("isBranch")
		.addField("enable");
		String sqlString = " SELECT"
								+ " b.RELA_ID"
								+ " ,b.ROLE_ID"
								+ " ,b.CREATE_BY"
								+ " ,b.CREATE_TIME"
								+ " ,a.RESOURCE_ID"
								+ " ,a.RESOURCE_CODE"
								+ " ,a.RESOURCE_NAME"
								+ " ,a.RESOURCE_LABEL"
								+ " ,IF(a.PARENT_RESOURCE_ID IS NULL, 0, a.PARENT_RESOURCE_ID)"
								+ " ,a.RESOURCE_TYPE"
								+ " ,a.SEQ"
								+ " ,a.URL"
								+ " ,a.IMAGE_PATH"
								+ " ,a.OPEN_MODE"
								+ " ,a.FULLPATH"
								+ " ,a.IS_BRANCH"
								+ " ,a.ENABLE"
							+ " FROM"
								+ " gxwl_sys_resource a LEFT JOIN gxwl_sys_roleresource b"
							+ " ON a.RESOURCE_ID = b.RESOURCE_ID"
								+ " AND b.ROLE_ID = " + roleId
							+ " WHERE"
								+ " IF(a.ENABLE IS NULL, 'N', a.ENABLE) = 'Y'";
		return selectBySql(sqlString, fields);
	}

}
