package com.nateiot.cis.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEsModiary;
import com.nateiot.cis.repository.CisEsModiaryDao;
import com.nateiot.cis.service.CisEsModiaryService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 考核督办 -- 民情日记统计
 * 
 * @author guohuawen
 *
 */
@Service(value = "cisEsModiaryService")
@Transactional(readOnly = true)
public class CisEsModiaryServiceImpl extends
         BaseServiceImpl<CisEsModiaryDao, CisEsModiary, Integer> implements
         CisEsModiaryService {
	
	@Autowired
	private CisEsModiaryDao cisEsModiaryDao;
	
	@Autowired
	public CisEsModiaryServiceImpl(CisEsModiaryDao cisEsModiaryDao) {
		super(cisEsModiaryDao);
	}
	
	/**
	 * 查询经济户口统计表数据
	 */
	public Map<String, Object> modiaryFormSearch(
			Map<String, SearchFilter> conditions){
		resetResultMap();
		try{
			List<Map<String, Object>> map = cisEsModiaryDao.moDiaryFormSearch(conditions);
			
			resultMap.put(RESULT_ROWS, map);
			setResultStatus(0, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 处理Excel导出业务逻辑(民情日记统计表)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void moDiaryFormExport(Map<String, SearchFilter> conditions, 
			HSSFWorkbook workbook){
		Map<String, Object> map = modiaryFormSearch(conditions);
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("rows");
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		sheet.setColumnWidth(0, 5 * 256);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);
		sheet.setColumnWidth(6, 20 * 256);
		sheet.setColumnWidth(7, 20 * 256);
		sheet.setColumnWidth(8, 20 * 256);
		
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("民情日记统计表");
		Date date = new Date();
		row.createCell(1).setCellValue(date);
		
		HSSFRow row1 = sheet.createRow(1);
		row1.createCell(0).setCellValue("编码");
		row1.createCell(1).setCellValue("所属机构");
		row1.createCell(2).setCellValue("姓名");
		row1.createCell(3).setCellValue("优");
		row1.createCell(4).setCellValue("良");
		row1.createCell(5).setCellValue("中");
		row1.createCell(6).setCellValue("差");
		row1.createCell(7).setCellValue("合计分值");
		
		int i = 2;
		int mark = 1;
		int total = 0;
		for(Map<String, Object> data : list){
			HSSFRow row2 = sheet.createRow(i++);
			row2.createCell(0).setCellValue(mark++);
			row2.createCell(1).setCellValue(Integer.parseInt(data.get("modiaryCode").toString()));
			row2.createCell(2).setCellValue(Integer.parseInt(data.get("modiaryUnit").toString()));
			row2.createCell(3).setCellValue(Integer.parseInt(data.get("modiaryName").toString()));
			row2.createCell(4).setCellValue(Integer.parseInt(data.get("actor").toString()));
			row2.createCell(5).setCellValue(Integer.parseInt(data.get("fine").toString()));
			row2.createCell(6).setCellValue(Integer.parseInt(data.get("middle").toString()));
			row2.createCell(7).setCellValue(Integer.parseInt(data.get("bad").toString()));
			
			total = Integer.parseInt(data.get("actor").toString())
				  + Integer.parseInt(data.get("fine").toString())
				  + Integer.parseInt(data.get("middle").toString())
				  + Integer.parseInt(data.get("bad").toString());
			
			row2.createCell(8).setCellValue(total);
		}
		
		
	}
}
