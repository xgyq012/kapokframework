package com.nateiot.base.service;

import java.util.Map;

import com.nateiot.base.domain.GxwlSysClientVersion;
import com.nateiot.base.repository.GxwlSysClientVersionDao;
import com.nateiot.core.service.BaseService;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysClientVersionService extends BaseService<GxwlSysClientVersionDao, GxwlSysClientVersion, Integer> {

	public Map<String, Object> checkGxwlSysClientVersion(String fileNumber, String versionNumber);

	public GxwlSysClientVersion findByFileNumberAndVersionNumber(String fileNumber, String versionNumber);

	public Map<String, Object> findByFileNumberAndIsLast(String fileNumber);
}
