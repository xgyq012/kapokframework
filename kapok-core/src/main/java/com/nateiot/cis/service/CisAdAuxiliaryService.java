package com.nateiot.cis.service;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisAdAuxiliary;
import com.nateiot.cis.repository.CisAdAuxiliaryDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 辅助决策 
 * 
 * @author Administrator
 *
 */
public interface CisAdAuxiliaryService extends
	BaseService<CisAdAuxiliaryDao, CisAdAuxiliary, Integer> {
	
	
	/**
	 *  人口信息统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> rkxxtj( Map<String, SearchFilter> conditions,Pageable pageable);
	

	/**
	 *  房屋管理信息统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> fwgltj( Map<String, SearchFilter> conditions,Pageable pageable);
	 
	/**
	 *  人员事件处理统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> event( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 *  党群建设统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> party( Map<String, SearchFilter> conditions,Pageable pageable);
	

	/**
	 *  应急管理统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> emergency( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 *  机构人员统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> orgPeople( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 *  特殊人群统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> crowd( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 *  综合治理统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> govern( Map<String, SearchFilter> conditions,Pageable pageable);
	
	
	/**
	 * 综合治理数据统计(查询) 
	 * 
	 * @param req
	 * @return
	 */
	public Map<String, Object> overHaulFormSearch(
			Map<String, SearchFilter> conditions);
	
	/**
	 * 综合治理数据统计表导出
	 * 
	 *  @param conditions
	 *  @return
	 */
	public void overHaulFormExport(Map<String, SearchFilter> conditions,
			HSSFWorkbook workbook);
	
}
