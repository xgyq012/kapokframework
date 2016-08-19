package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisEsReportStatistics;
import com.nateiot.cis.repository.CisEsReportStatisticsDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 考核督办 -- 报表统计
 *  
 *  @author Guohw
 */
public interface CisEsReportStatisticsService 
		extends BaseService<CisEsReportStatisticsDao, CisEsReportStatistics, Integer>{
	
	/**
	 * 民情日记统计
	 * 
	 *  @param conditions, pageable
	 *  @return
	 */
	public Map<String, Object> diarySearch(String timeGte, String timeLte, Map<String, SearchFilter> conditions, Pageable pageable);
	
	/**
	 * 积分统计
	 * 
	 * @param conditions, pageable
	 * @return
	 */
	public Map<String, Object> scoreSearch(String timeGte, String timeLte, Map<String, SearchFilter> conditions, Pageable pageable);
	
	/**
	 * 民情日记报表导出 
	 */
	public void statisticsDiaryFormExport(Map<String, SearchFilter> conditions, HSSFWorkbook workbook);
	
	/**
	 * 积分统计报表导出
	 */
	public void statisticsScoreFormExport(Map<String, SearchFilter> conditions, HSSFWorkbook workbook);

	
}
