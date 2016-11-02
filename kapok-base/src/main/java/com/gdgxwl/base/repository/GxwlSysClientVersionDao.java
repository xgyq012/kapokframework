package com.gdgxwl.base.repository;

import com.gdgxwl.base.domain.GxwlSysClientVersion;
import com.gdgxwl.core.repository.BaseDao;

import java.util.List;

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
