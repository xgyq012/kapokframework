package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.common.SessionUtil;
import com.gdgxwl.base.domain.GxwlSysDataP;
import com.gdgxwl.base.domain.GxwlSysResource;
import com.gdgxwl.base.repository.GxwlSysDataPDao;
import com.gdgxwl.base.repository.GxwlSysResourceDao;
import com.gdgxwl.base.service.GxwlSysDataPService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysDataPService")
@Transactional(readOnly = true)
public class GxwlSysDataPServiceImpl extends
		BaseServiceImpl<GxwlSysDataPDao, GxwlSysDataP, Integer> implements
		GxwlSysDataPService {

	@Autowired
	private GxwlSysDataPDao gxwlSysDataPDao;
	
	@Autowired
	private GxwlSysResourceDao gxwlSysResourceDao;

	@Autowired
	public GxwlSysDataPServiceImpl(GxwlSysDataPDao gxwlSysDataPDao) {
		super(gxwlSysDataPDao);
	}

	@Override
	public Map<String, Object> getCurrentUserResourceHeaderByResourceCode(String resourceCode) {
		resetResultMap();
		try {
			GxwlSysResource resource = gxwlSysResourceDao.findByResourceCode(resourceCode);
			List<Map<String, Object>> datas = gxwlSysDataPDao
					.getCurrentUserDataByResourceId(SessionUtil
							.getCurrentUser().getUserId(), resource.getId());
			Set<String> fields = new LinkedHashSet<String>();
			Set<String> fieldsName = new LinkedHashSet<String>();
			for (Map<String, Object> map : datas) {
				String columnNames = map.get("columnNames") == null ? "" : map.get("columnNames").toString();
				if (!StringUtils.isEmpty(columnNames)) {
					for (String columnName : StringUtils.split(columnNames, ",")) {
						if (!StringUtils.isEmpty(columnName)) {
							fields.add(StringUtils.strip(columnName));
						}
					}
				}
				String titleNames = map.get("titleNames") == null ? "" : map.get("titleNames").toString();
				if (!StringUtils.isEmpty(titleNames)) {
					for (String titleName : StringUtils.split(titleNames, ",")) {
						if (!StringUtils.isEmpty(titleName)) {
							fieldsName.add(StringUtils.strip(titleName));
						}
					}
				}

			}
			String[] a = new String[fields.size()];
			String[] b = new String[fieldsName.size()];
			fields.toArray(a);
			fieldsName.toArray(b);
			
			List<String[]> titleList = new ArrayList<String[]>();
			String[] strs = null;
			for (int i = 0; i < a.length; i++) {
				strs = new String[2];
				strs[0] = a[i];
				try {
					strs[1] = b[i];
				} catch (Exception e) {
					strs[1] = "";
				}
				titleList.add(strs);
			}
			setResultStatus(0, "查询成功");
			resultMap.put("headers", titleList);
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "系统出错: " + e.getMessage());
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getCurrentUserResourceSqlWhereByResourceCode(String resourceCode) {
		resetResultMap();
		try {
			GxwlSysResource resource = gxwlSysResourceDao.findByResourceCode(resourceCode);
			List<Map<String, Object>> datas = gxwlSysDataPDao
					.getCurrentUserDataByResourceId(SessionUtil
							.getCurrentUser().getUserId(), resource.getId());
			StringBuffer sqlWheres = new StringBuffer();
			for (Map<String, Object> map : datas) {
				String sqlWhere = map.get("sqlWhere") == null ? "" : map.get("sqlWhere").toString();
				sqlWhere =  StringUtils.stripToEmpty(sqlWhere);
				if (!StringUtils.isEmpty(sqlWhere)) {
					sqlWheres.append(" or (" + sqlWhere + ")");
				}
			}
			
			setResultStatus(0, "查询成功");
			resultMap.put("sqlWhere", sqlWheres.length() > 0 ? sqlWheres.substring(4) : "");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "系统出错: " + e.getMessage());
		}
		return resultMap;
	}	
	
}
