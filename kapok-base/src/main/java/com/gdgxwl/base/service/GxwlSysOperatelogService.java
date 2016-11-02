package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysOperatelog;
import com.gdgxwl.base.repository.GxwlSysOperatelogDao;
import com.gdgxwl.core.service.BaseService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

public interface GxwlSysOperatelogService extends
		BaseService<GxwlSysOperatelogDao, GxwlSysOperatelog, Integer> {

	/**
	 * 处理Excel导出业务逻辑
	 * 
	 * @param logs
	 * @param workbook
	 */
	public void doExcelExport(List<GxwlSysOperatelog> logs,
			HSSFWorkbook workbook);
	
	/**
	 * 清除六个月以前的日志
	 * 
	 * @return
	 */
	public Map<String, Object> clearSixMonthsAgoLogs();
	
}
