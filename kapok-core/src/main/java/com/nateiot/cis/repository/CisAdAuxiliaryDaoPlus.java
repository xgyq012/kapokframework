package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nateiot.core.common.persistence.SearchFilter;

/**
 * 辅助决策 
 * 
 * @author Administrator
 *
 */
public interface CisAdAuxiliaryDaoPlus {
	
	
	/**
	 * 人口信息统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> rkxxtj( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 * 房屋管理信息统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> fwgltj( Map<String, SearchFilter> conditions,Pageable pageable);
	
	
	/**
	 * 综合治理数据统计(查询) 
	 * 
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> overHaulFormSearch(
			Map<String, SearchFilter> conditions);

	/**
	 * 人员事件处理统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> event( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 * 党群建设统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> party( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 * 应急管理统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> emergency( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 * 机构人员统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> orgPeople( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 * 特殊人群统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> crowd( Map<String, SearchFilter> conditions,Pageable pageable);
	
	/**
	 * 综合治理统计
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> govern( Map<String, SearchFilter> conditions,Pageable pageable);
	
}
