package com.nateiot.cis.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEsReportStatistics;
import com.nateiot.cis.repository.CisEsReportStatisticsDao;
import com.nateiot.cis.service.CisEsReportStatisticsService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 * 服务办事 -- 民情日记
 * 
 *  @author Guohw
 */
@Service(value = "cisEsReportStatistics")
@Transactional(readOnly=true)
public class CisEsReportStatisticsServiceImpl 
		extends BaseServiceImpl<CisEsReportStatisticsDao, CisEsReportStatistics, Integer>
		implements CisEsReportStatisticsService{
	
	@Autowired
	private CisEsReportStatisticsDao cisEsReportStatisticsDao;
	
	@Autowired
	public CisEsReportStatisticsServiceImpl(CisEsReportStatisticsDao d) {
		super(d);
	}

	/**
	 * 民情日记统计 
	 */
	@Override
	public Map<String, Object> diarySearch(String timeGte, String timeLte, 
			Map<String, SearchFilter> conditions, Pageable pageable){
		resetResultMap();
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			///获取当前月第一天：
	        Calendar c = Calendar.getInstance();  
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        String first = format.format(c.getTime());
	         
	        //获取当前月最后一天
	        Calendar ca = Calendar.getInstance();
	        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
	        String last = format.format(ca.getTime());
			
			Page<Map<String, Object>> page = cisEsReportStatisticsDao.diarySearch(timeGte, timeLte,
					conditions, pageable, first, last);
			
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "民情日记统计成功。");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "民情日记统计出错。");
		}
		return resultMap;
	}
	

	/**
	 * 民情日记报表(导出) 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void statisticsDiaryFormExport(Map<String, SearchFilter> conditions,
			HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
	}
	
	
	/**
	 * 积分统计
	 */
	@Override
	public Map<String, Object> scoreSearch(String timeGte, String timeLte, 
			Map<String, SearchFilter> conditions, Pageable pageable){
		resetResultMap();
		try{
			//截取网格ID
//			List<Integer> meshIds = null;
//			if(StringUtils.isNotBlank(ids)){
//				meshIds = new ArrayList<Integer>();
//				String[] array = ids.split(",");
//				for(String id : array){
//					if(id != null){
//						meshIds.add(Integer.parseInt(id));
//					}
//				}
//			}
			
//			System.out.println(meshIds);
			
			//获取当月第一天、最后一天
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			///获取当前月第一天：
	        Calendar c = Calendar.getInstance();  
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        String first = format.format(c.getTime());
	         
	        //获取当前月最后一天
	        Calendar ca = Calendar.getInstance();
	        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
	        String last = format.format(ca.getTime());
			
			Page<Map<String, Object>> page = cisEsReportStatisticsDao.scoreSearch(timeGte, timeLte, 
					conditions, pageable, first, last);
			
			resultMap.put(RESULT_ROWS, page.getContent());
//			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "积分统计成功。");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "积分统计出错。");
		}
		return resultMap;
	}

	/**
	 * 积分统计报表(导出) 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void statisticsScoreFormExport(Map<String, SearchFilter> conditions,
			HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		
	}

}
