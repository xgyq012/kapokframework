package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.domain.GxwlSysOperatelog;
import com.gdgxwl.base.repository.GxwlSysOperatelogDao;
import com.gdgxwl.base.service.GxwlSysOperatelogService;
import com.gdgxwl.core.common.web.SearchUtil;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "gxwlSysOperatelogService")
@Transactional(readOnly = true)
public class GxwlSysOperatelogServiceImpl extends
		BaseServiceImpl<GxwlSysOperatelogDao, GxwlSysOperatelog, Integer>
		implements GxwlSysOperatelogService {

	@Autowired
	public GxwlSysOperatelogDao gxwlSysOperatelogDao;

	@Autowired
	public GxwlSysOperatelogServiceImpl(
			GxwlSysOperatelogDao gxwlSysOperatelogDao) {
		super(gxwlSysOperatelogDao);
	}

	@Override
	public void doExcelExport(List<GxwlSysOperatelog> logs,
			HSSFWorkbook workbook) {
		int i = 1;
		HSSFSheet sheet = workbook.getSheetAt(0);
		sheet.setColumnWidth(0, 10 * 256);
		sheet.setColumnWidth(1, 10 * 256);
		sheet.setColumnWidth(2, 65 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFDataFormat dataFormat = workbook.createDataFormat();
		for (GxwlSysOperatelog log : logs) {
			HSSFRow row = sheet.createRow(i++);
			row.createCell(0).setCellValue(log.getRealname());
			row.createCell(1).setCellValue(log.getOperateType());
			row.createCell(2).setCellValue(log.getLogDesc());
			HSSFCell cell3 = row.createCell(3);
			cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
			cell3.setCellStyle(cellStyle);
			cell3.setCellValue(log.getOperateTime());
		}
	}

	@Transactional
	@Override
	public Map<String, Object> clearSixMonthsAgoLogs() {
		resetResultMap();
		try {
			DateTime sixMonthsAgo = DateTime.now().minusMonths(6);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("operateTime_LT_Timestamp", sixMonthsAgo.toString("yyyy-MM-dd HH:mm:ss"));
			
			Specification<GxwlSysOperatelog> spec = SearchUtil.getSpecification(GxwlSysOperatelog.class, params);
			List<GxwlSysOperatelog> logs = gxwlSysOperatelogDao.findAll(spec);
			
			gxwlSysOperatelogDao.delete(logs);
			
			setResultStatus(0, "清除六个月前的日志成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "清除六个月前的日志成功时系统出错: " + e.getMessage());
		}
		return resultMap;
	}

}
