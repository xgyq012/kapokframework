package com.nateiot.core.service;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.nateiot.core.common.excel.ExcelFile;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.BaseDao;

/**
 * @author Will WM. Zhang
 * 
 */
public interface BaseService<D extends BaseDao<E, ID>, E, ID extends Serializable> {

	public static final String RESULT_CODE = "resultCode";
	public static final String RESULT_MSG = "resultMsg";
	public static final String ERROR_CODE = "errorCode";
	public static final String RESULT_TOTAL = "total";
	public static final String RESULT_ROW = "row";
	public static final String RESULT_ROWS = "rows";
	

	/**
	 * 保存一个实体对象
	 * 
	 * @param entity
	 * @return
	 */
	public Map<String, Object> doSave(E entity);

	/**
	 * 保存实体对象集合里所有的实体对象
	 * 
	 * @param entities
	 * @return
	 */
	public Map<String, Object> doSave(Iterable<E> entities);

	/**
	 * 根据主键查找一个实体对象
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> doSelect(ID id);

	/**
	 * @param spec
	 * @return
	 */
	public Map<String, Object> doSelect(Specification<E> spec);

	/**
	 * 查找所有实体
	 * 
	 * @return 返回所有实体
	 */
	public Map<String, Object> doSearch();

	/**
	 * 根据主键集合里所有的主键查找出对应的实体对象
	 * 
	 * @param ids
	 * @return 返回主键集合里对应的实体对象的集合
	 */
	public Map<String, Object> doSearch(Iterable<ID> ids);

	/**
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> doSearch(Pageable pageable);
	
	/**
	 * @param sort
	 * @return
	 */
	public Map<String, Object> doSearch(Sort sort);

	/**
	 * @param spec
	 * @return
	 */
	public Map<String, Object> doSearch(Specification<E> spec);

	/**
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> doSearch(Specification<E> spec, Pageable pageable);

	/**
	 * @param spec
	 * @param sort
	 * @return
	 */
	public Map<String, Object> doSearch(Specification<E> spec, Sort sort);
	
	/**
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> doSearchBySql(Pageable pageable);
	
	/**
	 * @param sort
	 * @return
	 */
	public Map<String, Object> doSearchBySql(Sort sort);

	/**
	 * @param spec
	 * @return
	 */
	public Map<String, Object> doSearchBySql(Map<String, SearchFilter> conditions);

	/**
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Map<String, Object> doSearchBySql(Map<String, SearchFilter> conditions, Pageable pageable);

	/**
	 * @param spec
	 * @param sort
	 * @return
	 */
	public Map<String, Object> doSearchBySql(Map<String, SearchFilter> conditions, Sort sort);
	
	/**
	 * 根据主键删除一个实体对象
	 * 
	 * @param id
	 */
	public Map<String, Object> doDelete(ID id);

	/**
	 * 根据实体对象删除一个实体对象
	 * 
	 * @param entity
	 */
	public Map<String, Object> doDelete(E entity);

	/**
	 * 根据实体对象集合删除一个实体对象集合里所有的实体对象
	 * 
	 * @param entities
	 */
	public Map<String, Object> doDelete(Iterable<E> entities);

	/**
	 * 删除所有实体对象
	 */
	public Map<String, Object> doDeleteAll();
	
	
	/**
	 * excel导入
	 * 
	 * @param excelFile
	 * @return
	 */
	public Map<String, Object> excelImport(ExcelFile excelFile);
	
}
