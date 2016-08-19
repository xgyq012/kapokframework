package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.service.CisEsReportStatisticsService;
import com.nateiot.core.common.web.SearchUtil;
import com.nateiot.core.web.ExcelExportController;


/**
 * 考核督办 -- 报表统计
 *  
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/reportStatistics")
public class CisEsReportStatisticsController extends ExcelExportController {
	
	@Autowired
	private CisEsReportStatisticsService cisEsReportStatisticsService;
	
	/**
	 * 加载视图(民情日记统计)
	 * 
	 *  @param
	 *  
	 */
	@RequestMapping(value = "/diaryList")
	public String diaryList(){
		return "cis/es/statisticsDiary/statisticsDiary";
	}
	
	/**
	 * 查询(民情日记统计)
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/diarySearch")
	@ResponseBody
	public Map<String, Object> search(
			@RequestParam(value="timeGte", defaultValue="", required=false) String timeGte, 
			@RequestParam(value="timeLte", defaultValue="", required=false) String timeLte, 
			HttpServletRequest req){
		return cisEsReportStatisticsService.diarySearch(
				timeGte, 
				timeLte, 
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	/**
	 * 加载视图(积分统计)
	 * 
	 *  @param 
	 */
	@RequestMapping(value = "/scoreList")
	public String scoreList(){
		return "cis/es/statisticsScore/statisticsScore";
	}
	
	/**
	 * 查询(积分统计) 
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/scoreSearch")
	@ResponseBody
	public Map<String, Object> scoreSearch(
			@RequestParam(value="timeGte", defaultValue="", required=false) String timeGte, 
			@RequestParam(value="timeLte", defaultValue="", required=false) String timeLte, 
			HttpServletRequest req){
		return cisEsReportStatisticsService.scoreSearch(
				timeGte, 
				timeLte, 
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	// 统计表Excel导出
	@Override
	protected void doExcelExport(HSSFWorkbook workbook, String template,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//民情日记数据统计(导出报表)
		if(template.equals("statisticsDiary")){
			cisEsReportStatisticsService.statisticsDiaryFormExport(
					SearchUtil.getSearchFilters(request), workbook);
		}
		//积分统计(到处报表 )
		if(template.equals("statisticsScore")){
			cisEsReportStatisticsService.statisticsScoreFormExport(
					SearchUtil.getSearchFilters(request), workbook);
		}
	}

}
