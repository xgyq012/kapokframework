package com.gdgxwl.base.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Will WM. Zhang
 *
 */
public class BaseUtil {
	
	private static String realPath;
	
	private static String rootUrl ;
	

	
	public BaseUtil() {

	}

	public static String getSysRootPath() {
		String uploadFileRootPath = ConfigUtil.getConfigValue("SysConfig", "uploadFileRootPath");
		uploadFileRootPath = StringUtils.trimToEmpty(uploadFileRootPath);
		uploadFileRootPath = StringUtils.defaultIfBlank(uploadFileRootPath, FileUtils.getUserDirectoryPath().concat("/LcissUpload"));
		uploadFileRootPath = StringUtils.replace(uploadFileRootPath, "\\", "/");
		uploadFileRootPath = StringUtils.stripEnd(uploadFileRootPath, "/");
		return uploadFileRootPath;
	}

	public static String getRealPath() {
		return realPath;
	}

	public static void setRealPath(String realPath) {
		BaseUtil.realPath = realPath;
	}
	

	public static String getRootUrl() {
		return rootUrl;
	}

	public static void setRootUrl(String rootUrl) {
		BaseUtil.rootUrl = rootUrl;
	}


}
