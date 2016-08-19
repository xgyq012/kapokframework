package com.nateiot.core.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.impl.ResultFields;

/**
 * @author Will WM. Zhang
 * 
 */
public interface BaseDaoPlus {
	
	public List<Map<String, Object>> selectBySql(String sqlString);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, Sort sort);
	
	public List<Map<String, Object>> selectBySql(String sqlString, String groupBy);
	
	public List<Map<String, Object>> selectBySql(String sqlString, String groupBy, Sort sort);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Sort sort);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Sort sort);
	
	
	public List<Map<String, Object>> selectBySql(String sqlString, ResultFields resultFields);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, ResultFields resultFields);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, ResultFields resultFields);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, Sort sort, ResultFields resultFields);
	
	public List<Map<String, Object>> selectBySql(String sqlString, String groupBy, ResultFields resultFields);
	
	public List<Map<String, Object>> selectBySql(String sqlString, String groupBy, Sort sort, ResultFields resultFields);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Sort sort, ResultFields resultFields);
	
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Sort sort, ResultFields resultFields);
	
	
	/* ******************** */
	
	
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Pageable pageable);
	
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, Pageable pageable);
	
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, String groupBy, Pageable pageable);

	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable);
	

	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Pageable pageable, ResultFields resultFields);
	
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, Pageable pageable, ResultFields resultFields);
	
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, String groupBy, Pageable pageable, ResultFields resultFields);

	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable, ResultFields resultFields);

	
	/* ******************** */
	
	
	public Map<String, Object> selectOneBySql(String sqlString);
	
	public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions);

	public Map<String, Object> selectOneBySql(String sqlString, String groupBy);

	public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy);
	

	public Map<String, Object> selectOneBySql(String sqlString, ResultFields resultFields);
	
	public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions, ResultFields resultFields);

	public Map<String, Object> selectOneBySql(String sqlString, String groupBy, ResultFields resultFields);

	public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, ResultFields resultFields);	
	
}
