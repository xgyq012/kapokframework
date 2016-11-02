package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysClientVersion;
import com.gdgxwl.base.repository.GxwlSysClientVersionDao;
import com.gdgxwl.core.service.BaseService;

import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysClientVersionService extends BaseService<GxwlSysClientVersionDao, GxwlSysClientVersion, Integer> {

	public Map<String, Object> checkGxwlSysClientVersion(String fileNumber, String versionNumber);

	public GxwlSysClientVersion findByFileNumberAndVersionNumber(String fileNumber, String versionNumber);

	public Map<String, Object> findByFileNumberAndIsLast(String fileNumber);
}
