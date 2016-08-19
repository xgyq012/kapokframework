package com.nateiot.cis.service;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.nateiot.cis.domain.CisEsModiary;
import com.nateiot.cis.repository.CisEsModiaryDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 考核督办 -- 民情日记统计
 * 
 * @author Administrator
 *
 */
public interface CisEsModiaryService extends
	BaseService<CisEsModiaryDao, CisEsModiary, Integer> {
	
	/**
	 * 查询经济户口统计表数据
	 */
	public Map<String, Object> modiaryFormSearch(Map<String, SearchFilter> conditions);
	
	/**
	 * 处理Excel导出业务逻辑(民情日记统计表)
	 * 
	 * @param users
	 * @param workbook
	 */
	public void moDiaryFormExport(Map<String, SearchFilter> conditions,
			HSSFWorkbook workbook);
}
