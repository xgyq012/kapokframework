package com.gdgxwl.base.repository.impl;

import com.gdgxwl.base.repository.GxwlSysResourceDaoPlus;
import com.gdgxwl.core.repository.impl.BaseDaoImpl;
import com.gdgxwl.core.repository.impl.ResultFields;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public class GxwlSysResourceDaoImpl extends BaseDaoImpl implements
		GxwlSysResourceDaoPlus {

	@Override
	public List<Map<String, Object>> getCurrentUserMenuByParenResourceId(
			Integer userId, Integer parentResourceId) {
		
		ResultFields fields = new ResultFields();
		fields.addField("id")
		.addField("resourceCode")
		.addField("resourceName")
		.addField("resourceLabel")
		.addField("parentResourceId")
		.addField("resourceType")
		.addField("seq")
		.addField("imagePath")
		.addField("openMode")
		.addField("fullpath")
		.addField("enable")
		.addField("state")
		.addField("url")
		.addField("systemType")
		.addField("resourceDesc")
		.addField("remark");
		
		String sqlString = " SELECT"
								+ " v.resource_id"
								+ " ,v.resource_code"
								+ " ,v.resource_name"
								+ " ,v.resource_label"
								+ " ,v.parent_resource_id"
								+ " ,v.resource_type"
								+ " ,v.seq"
								+ " ,v.image_path"
								+ " ,v.open_mode"
								+ " ,v.fullpath"
								+ " ,v.enable"
							    + " ,if(v.is_branch = 'Y'"
							    	+ " AND ("
							    		+ " SELECT"
							    			+ " COUNT(1)"
							    		+ " FROM"
							    			+ " gxwl_sys_resource r"
							    		+ " WHERE"
							    			+ " r.PARENT_RESOURCE_ID = v.RESOURCE_ID"
							    			+ " AND r.resource_type = 'menu'"
							    		+ ") > 0 ,"
							    	+ " 'closed' ,"
							    	+ " 'open')"
								+ " ,v.url"
								+ " ,v.system_type"
								+ " ,v.resource_desc"
								+ " ,v.remark"
							+ " FROM"
								+ " v_gxwl_sys_userresource v"
							+ " where"
								+ " v.user_id = " + userId
								+ " and v.resource_type = 'menu'"
								+ " and v.enable = 'Y'";
		if (parentResourceId == 0) {
			sqlString = sqlString + " and v.parent_resource_id is null";
		} else {
			sqlString = sqlString + " and v.parent_resource_id = " + parentResourceId;
		}
		sqlString += " order by seq asc";
		
		return selectBySql(sqlString, fields);
	}
	
	@Override
	public List<Map<String, Object>> getCurrentUserMenu(
			Integer userId) {
		
		ResultFields fields = new ResultFields();
		fields.addField("id")
		.addField("resourceCode")
		.addField("resourceName")
		.addField("resourceLabel")
		.addField("parentResourceId")
		.addField("resourceType")
		.addField("seq")
		.addField("imagePath")
		.addField("openMode")
		.addField("fullpath")
		.addField("enable")
		.addField("state")
		.addField("url")
		.addField("systemType")
		.addField("resourceDesc")
		.addField("remark");
		
		String sqlString = " SELECT"
								+ " v.resource_id"
								+ " ,v.resource_code"
								+ " ,v.resource_name"
								+ " ,v.resource_label"
								+ " ,v.parent_resource_id"
								+ " ,v.resource_type"
								+ " ,v.seq"
								+ " ,v.image_path"
								+ " ,v.open_mode"
								+ " ,v.fullpath"
								+ " ,v.enable"
							    + " ,if(v.is_branch = 'Y'"
							    	+ " AND ("
							    		+ " SELECT"
							    			+ " COUNT(1)"
							    		+ " FROM"
							    			+ " gxwl_sys_resource r"
							    		+ " WHERE"
							    			+ " r.PARENT_RESOURCE_ID = v.RESOURCE_ID"
							    		//	+ " AND r.resource_type = 'menu'"
							    		+ ") > 0 ,"
							    	+ " 'closed' ,"
							    	+ " 'open')"
								+ " ,v.url"
								+ " ,v.system_type"
								+ " ,v.resource_desc"
								+ " ,v.remark"
							+ " FROM"
								+ " v_gxwl_sys_userresource v"
							+ " where"
								+ " v.user_id = " + userId
								//+ " and v.resource_type = 'menu'"
								+ " and v.enable = 'Y'";
 
		sqlString += " order by seq asc";
		
		return selectBySql(sqlString, fields);
	}

	@Override
	public List<Map<String, Object>> getCurrentUserMenuByResourceType(
			Integer userId, String resourceType, String systemType) {

		String sqlString = " SELECT"
				+ " v.resource_id as resourceId"
				+ " ,v.resource_code as resourceCode"
				+ " ,v.resource_name as resourceName"
				+ " ,v.resource_label as resourceLabel"
				+ " ,v.parent_resource_id as parentResourceId"
				+ " ,v.resource_type as resourceType"
				+ " ,v.seq as seq"
				+ " ,v.image_path as imagePath"
				+ " ,v.fullpath as fullpath"
				+ " ,v.url as url"
				+ " ,v.system_type as systemType"
				+ " ,v.resource_desc as resourceDesc"
				+ " ,v.remark"
				+ " FROM"
				+ " v_gxwl_sys_userresource v"
				+ " where"
				+ " v.user_id = " + userId
				+ " and v.resource_type in (" + resourceType + ")"
				+ " and v.enable = 'Y'";

		if (StringUtils.isNotBlank(systemType)) {
			sqlString += " and v.system_type in (" + systemType + ")";
		}

		return selectBySql(sqlString);
	}
	
	@Override
	public List<Map<String, Object>> getCurrentUserMenuByModule(
			Integer userId,Integer menuId) {
		
		ResultFields fields = new ResultFields();
		fields.addField("id")
		.addField("resourceCode")
		.addField("resourceName")
		.addField("resourceLabel")
		.addField("parentResourceId")
		.addField("resourceType")
		.addField("seq")
		.addField("imagePath")
		.addField("openMode")
		.addField("fullpath")
		.addField("enable")
		.addField("state")
		.addField("url")
		.addField("systemType")
		.addField("resourceDesc")
		.addField("remark");
		
		String sqlString = " SELECT"
								+ " v.resource_id"
								+ " ,v.resource_code"
								+ " ,v.resource_name"
								+ " ,v.resource_label"
								+ " ,v.parent_resource_id"
								+ " ,v.resource_type"
								+ " ,v.seq"
								+ " ,v.image_path"
								+ " ,v.open_mode"
								+ " ,v.fullpath"
								+ " ,v.enable"
							    + " ,if(v.is_branch = 'Y'"
							    	+ " AND ("
							    		+ " SELECT"
							    			+ " COUNT(1)"
							    		+ " FROM"
							    			+ " gxwl_sys_resource r"
							    		+ " WHERE"
							    			+ " r.PARENT_RESOURCE_ID = v.RESOURCE_ID"
							    		//	+ " AND r.resource_type = 'menu'"
							    		+ ") > 0 ,"
							    	+ " 'closed' ,"
							    	+ " 'open')"
								+ " ,v.url"
								+ " ,v.system_type"
								+ " ,v.resource_desc"
								+ " ,v.remark"
							+ " FROM"
								+ " v_gxwl_sys_userresource v"
							+ " where"
								+ " v. FULLPATH  like concat("+menuId+",'.', '%')  "
								+ " and v.user_id = " + userId
								//+ " and v.resource_type = 'menu'"
								+ " and v.enable = 'Y'";
 
		sqlString += " order by seq asc";
		
		return selectBySql(sqlString, fields);
	}

	@Override
	public List<Map<String, Object>> getCurrentUserMenuByModuleParent(Integer userId, Integer menuId) {
		 
		ResultFields fields = new ResultFields();
		fields.addField("id")
		.addField("resourceCode")
		.addField("resourceName")
		.addField("resourceLabel")
		.addField("parentResourceId")
		.addField("resourceType")
		.addField("seq")
		.addField("imagePath")
		.addField("openMode")
		.addField("fullpath")
		.addField("enable")
		.addField("state")
		.addField("url")
		.addField("systemType")
		.addField("resourceDesc")
		.addField("remark");
		
		String sqlString = " SELECT"
								+ " v.resource_id"
								+ " ,v.resource_code"
								+ " ,v.resource_name"
								+ " ,v.resource_label"
								+ " ,v.parent_resource_id"
								+ " ,v.resource_type"
								+ " ,v.seq"
								+ " ,v.image_path"
								+ " ,v.open_mode"
								+ " ,v.fullpath"
								+ " ,v.enable"
							    + " ,if(v.is_branch = 'Y'"
							    	+ " AND ("
							    		+ " SELECT"
							    			+ " COUNT(1)"
							    		+ " FROM"
							    			+ " gxwl_sys_resource r"
							    		+ " WHERE"
							    			+ " r.PARENT_RESOURCE_ID = v.RESOURCE_ID"
							    		//	+ " AND r.resource_type = 'menu'"
							    		+ ") > 0 ,"
							    	+ " 'closed' ,"
							    	+ " 'open')"
								+ " ,v.url"
								+ " ,v.system_type"
								+ " ,v.resource_desc"
								+ " ,v.remark"
							+ " FROM"
								+ " v_gxwl_sys_userresource v"
							+ " where"
								+ " v.PARENT_RESOURCE_ID= " + menuId
								+ " and v.user_id = " + userId
								//+ " and v.resource_type = 'menu'"
								+ " and v.enable = 'Y'";
 
		sqlString += " order by seq asc";
		
		return selectBySql(sqlString, fields);
	}
	
	
	

}
