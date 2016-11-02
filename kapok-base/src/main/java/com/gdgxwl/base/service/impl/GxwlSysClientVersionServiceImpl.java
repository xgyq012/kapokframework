package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.domain.GxwlSysClientVersion;
import com.gdgxwl.base.repository.GxwlSysClientVersionDao;
import com.gdgxwl.base.service.GxwlSysClientVersionService;
import com.gdgxwl.base.service.GxwlSysDocService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 *
 */
@Service(value = "gxwlSysClientVersionService")
@Transactional(readOnly = true)
public class GxwlSysClientVersionServiceImpl extends
		BaseServiceImpl<GxwlSysClientVersionDao, GxwlSysClientVersion, Integer>
		implements GxwlSysClientVersionService {

	@Autowired
	private GxwlSysClientVersionDao gxwlSysClientVersionDao;
	
	@Autowired
	private GxwlSysDocService gxwlSysDocService;

	@Autowired
	public GxwlSysClientVersionServiceImpl(
			GxwlSysClientVersionDao gxwlSysClientVersionDao) {
		super(gxwlSysClientVersionDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> doSave(GxwlSysClientVersion clientVersion) {
		resetResultMap();
		try {
			if ("Y".equals(clientVersion.getIsLast())) {
				List<GxwlSysClientVersion> clientVersions = gxwlSysClientVersionDao.findByFileNumber(clientVersion.getFileNumber());
				for (GxwlSysClientVersion cv : clientVersions) {
					cv.setIsLast("N");
				}
			}
			setResultStatus(0, "保存成功");
			gxwlSysDocService.moveDoc(Integer.valueOf(clientVersion.getClientUrl()));
			resultMap.put(RESULT_ROW, gxwlSysClientVersionDao.save(clientVersion));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer id) {
		resetResultMap();
		try {
			GxwlSysClientVersion clientVersion = gxwlSysClientVersionDao.findOne(id);
			gxwlSysDocService.deleteDoc(Integer.valueOf(clientVersion.getClientUrl()));
			gxwlSysClientVersionDao.delete(id);
			setResultStatus(0, "删除成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}
	
	@Override
	public Map<String, Object> checkGxwlSysClientVersion(String fileNumber, String versionNumber) {
		resetResultMap();
		try {
			GxwlSysClientVersion clientVersion = gxwlSysClientVersionDao.findByFileNumberAndVersionNumberNotAndIsLast(fileNumber, versionNumber, "Y");
			if (clientVersion != null) {
				setResultStatus(1, "最新版本为：" + clientVersion.getVersionNumber());
				Map<String, String> map = new HashMap<String, String>();
				map.put("fileNumber", clientVersion.getFileNumber());
				map.put("versionDesc", clientVersion.getVersionDesc());
				map.put("fileName", clientVersion.getFileName());
				map.put("versionNumber", clientVersion.getVersionNumber());
				resultMap.put(RESULT_ROW, map);
			} else {
				setResultStatus(0, "您当前的版本为最新版本");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "检查时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

	@Override
	public GxwlSysClientVersion findByFileNumberAndVersionNumber(String fileNumber, String versionNumber) {
		return gxwlSysClientVersionDao.findByFileNumberAndVersionNumber(fileNumber, versionNumber);
	}
	
	@Override
	public Map<String, Object> findByFileNumberAndIsLast(String fileNumber) {
		resetResultMap();
		try {
			resultMap.put(RESULT_ROW, gxwlSysClientVersionDao.findByFileNumberAndIsLast(fileNumber, "Y"));
			setResultStatus(0, "获取数据成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取数据时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

}
