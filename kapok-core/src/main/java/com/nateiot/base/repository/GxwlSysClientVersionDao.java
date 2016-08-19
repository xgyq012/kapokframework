package com.nateiot.base.repository;

import java.util.List;

import com.nateiot.base.domain.GxwlSysClientVersion;
import com.nateiot.core.repository.BaseDao;

/**
 * @author Will WM. Zhang
 *
 */
public interface GxwlSysClientVersionDao extends
		BaseDao<GxwlSysClientVersion, Integer>, GxwlSysClientVersionDaoPlus {

	public List<GxwlSysClientVersion> findByFileNumber(String fileNumber);

	public GxwlSysClientVersion findByFileNumberAndVersionNumberNotAndIsLast(
			String fileNumber, String versionNumber, String isLast);

	public GxwlSysClientVersion findByFileNumberAndVersionNumber(
			String fileNumber, String versionNumber);
	
	public GxwlSysClientVersion findByFileNumberAndIsLast(String fileNumber, String isLast);

}
