package com.gdgxwl.base.common;

import com.gdgxwl.base.domain.GxwlSysConfig;
import com.gdgxwl.base.repository.GxwlSysConfigDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ConfigUtil {

	private static GxwlSysConfigDao gxwlSysConfigDao;

	private static List<GxwlSysConfig> sysConfigList;

	public ConfigUtil(GxwlSysConfigDao configDao) {
		ConfigUtil.gxwlSysConfigDao = configDao;
		syncConfig();
	}

	public static String getConfigValue(String configType, String configCode) {
		configType = StringUtils.defaultString(configType);
		configCode = StringUtils.defaultString(configCode);
		for (GxwlSysConfig sysConfig : sysConfigList) {
			if (configType.equals(sysConfig.getConfigType())
					&& configCode.equals(sysConfig.getConfigCode())) {
				return sysConfig.getConfigValue();
			}
		}
		return null;
	}

	public static String getConfigValue(String configCode) {
		configCode = StringUtils.defaultString(configCode);
		for (GxwlSysConfig sysConfig : sysConfigList) {
			if (configCode.equals(sysConfig.getConfigCode())) {
				return sysConfig.getConfigValue();
			}
		}
		return null;
	}	
	
	/**
	 * 从数据库同步系统配置
	 */
	public static void syncConfig() {
		ConfigUtil.sysConfigList = gxwlSysConfigDao.findAll();
	}

}
