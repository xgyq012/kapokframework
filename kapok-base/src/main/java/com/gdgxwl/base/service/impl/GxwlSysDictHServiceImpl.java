package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.common.DictUtil;
import com.gdgxwl.base.domain.GxwlSysDictH;
import com.gdgxwl.base.domain.GxwlSysDictL;
import com.gdgxwl.base.repository.GxwlSysDictHDao;
import com.gdgxwl.base.repository.GxwlSysDictLDao;
import com.gdgxwl.base.service.GxwlSysDictHService;
import com.gdgxwl.core.service.BaseService;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Service(value = "gxwlSysDictHService")
@Transactional(readOnly = true)
public class GxwlSysDictHServiceImpl extends
		BaseServiceImpl<GxwlSysDictHDao, GxwlSysDictH, Integer> implements
		GxwlSysDictHService,
		BaseService<GxwlSysDictHDao, GxwlSysDictH, Integer> {

	@Autowired
	private GxwlSysDictHDao gxwlSysDictHDao;
	
	@Autowired
	private GxwlSysDictLDao gxwlSysDictLDao;

	@Autowired
	public GxwlSysDictHServiceImpl(GxwlSysDictHDao gxwlSysDictHDao) {
		super(gxwlSysDictHDao);
	}
	
	@Override
	@Transactional
	public Map<String, Object> doSave(GxwlSysDictH dictH) {
		resetResultMap();
		try {
			Integer dictTypeId = dictH.getDictTypeId();
			if (dictTypeId == null) {
				gxwlSysDictHDao.save(dictH);
				List<GxwlSysDictL> dictLs = dictH.getGxwlSysDictLs();
				if (dictLs != null) {
					for (GxwlSysDictL dictL : dictLs) {
						dictL.setDictTypeId(dictH.getDictTypeId());
					}
				}
			} else {
				GxwlSysDictH d = gxwlSysDictHDao.findOne(dictTypeId);
				List<GxwlSysDictL> olds = d.getGxwlSysDictLs();
				List<GxwlSysDictL> news = dictH.getGxwlSysDictLs();
				if (news == null) {
					gxwlSysDictLDao.deleteInBatch(olds);
				} else {
					for (GxwlSysDictL dictL : olds) {
						if (!news.contains(dictL)) {
							gxwlSysDictLDao.delete(dictL);
						}
					}
				}
			}
			resultMap.put(RESULT_ROW, gxwlSysDictHDao.save(dictH));
			setResultStatus(0, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

	// 手机端同步数据字典
	@Override
	public Map<String, Object> getDictByDictTypeCode(String dictTypeCode,
			DateTime lastSyncTime) {
		resetResultMap();
		try {
			String dictTypeCodes = "'" + StringUtils.join(StringUtils.split(dictTypeCode, ","), "','") + "'";
			List<Map<String, Object>> dicts = gxwlSysDictHDao.getDictByDictTypeCode(dictTypeCodes);
			if (dicts.size() > 0) {
				Map<String, Object> dict = dicts.get(0);
				DateTime lastUpdateTime = new DateTime(dict.get("lastUpdateTime"));
				if (lastUpdateTime.compareTo(lastSyncTime) > 0) {
					resultMap.put(RESULT_ROWS, dicts);
				}
			}
			setResultStatus(0, "获取数据字典成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "获取数据字典时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> syncDict() {
		resetResultMap();
		try {
			DictUtil.syncDict();
			setResultStatus(0, "同步数据字典成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "同步数据字典时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

}
