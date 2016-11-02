package com.gdgxwl.base.repository.impl;

import com.gdgxwl.base.repository.GxwlSysOrgDaoPlus;
import com.gdgxwl.core.repository.impl.BaseDaoImpl;
import com.gdgxwl.core.repository.impl.Field.DataType;
import com.gdgxwl.core.repository.impl.ResultFields;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public class GxwlSysOrgDaoImpl extends BaseDaoImpl implements GxwlSysOrgDaoPlus {

	@Override
	public List<Map<String, Object>> findRoleByOrgId(Integer orgId) {
		ResultFields fields = new ResultFields();
		fields.addField("relaId")
		.addField("orgId")
		.addField("roleId")
		.addField("createrId")
		.addField("createTime", DataType.Timestamp)
		.addField("roleCode")
		.addField("roleName")
		.addField("isGranted")
		.addField("isInherited")
		.addField("remark");
		String sqlString = " SELECT"
								+ " a.RELA_ID"
								+ " ,a.ORG_ID"
								+ " ,a.ROLE_ID"
								+ " ,a.CREATE_BY"
								+ " ,a.CREATE_TIME"
								+ " ,b.ROLE_CODE"
								+ " ,b.ROLE_NAME"
								+ " ,a.IS_GRANTED"
								+ " ,a.IS_INHERITED"
								+ " ,a.REMARK"
							+ " FROM"
								+ " gxwl_sys_roleorg a,"
								+ " gxwl_sys_role b"
							+ " WHERE"
								+ " b.ROLE_ID = a.ROLE_ID"
								+ " AND a.ORG_ID = " + orgId;
		return selectBySql(sqlString, fields);
	}

	@Override
	public List<Map<String, Object>> findUserByOrgId(Integer orgId) {
		ResultFields fields = new ResultFields();
		fields.addField("relaId")
		.addField("orgId")
		.addField("userId")
		.addField("createrId")
		.addField("createTime", DataType.Timestamp)
		.addField("userName")
		.addField("realname")
		.addField("statusName")
		.addField("isMain")
		.addField("isManage");
		String sqlString = " SELECT"
								+ " a.RELA_ID"
								+ " ,a.ORG_ID"
								+ " ,a.USER_ID"
								+ " ,a.CREATE_BY"
								+ " ,a.CREATE_TIME"
								+ " ,b.USER_NAME"
								+ " ,b.REALNAME"
								+ " ,(SELECT"
										+ " l.DICT_NAME"
									+ " FROM"
										+ " gxwl_sys_dict_l h ,"
										+ " gxwl_sys_dict_l l"
									+ " WHERE"
										+ " l.DICT_CODE = b.STATUS"
										+ " AND l.DICT_TYPE_ID = h.DICT_TYPE_ID"
										+ " AND h.DICT_CODE = 'userStatus')"
								+ " ,a.IS_MAIN"
								+ " ,a.IS_MANAGE"
							+ " FROM"
								+ " gxwl_sys_orguser a ,"
								+ " gxwl_sys_user b"
							+ " WHERE"
								+ " b.USER_ID = a.USER_ID"
								+ " AND a.ORG_ID = " + orgId;
		return selectBySql(sqlString, fields);
	}

}
