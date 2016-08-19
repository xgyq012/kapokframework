package com.nateiot.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.core.common.excel.ExcelFile;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.BaseDao;
import com.nateiot.core.service.BaseService;

/**
 * @author Will WM. Zhang
 * 
 */
@Transactional(readOnly = true)
public class BaseServiceImpl<D extends BaseDao<E, ID>, E, ID extends Serializable>
		implements BaseService<D, E, ID> {

	private final D d;

	protected Map<String, Object> resultMap;

	protected void resetResultMap() {
		resultMap.clear();
		resultMap.put(RESULT_CODE, 0);
		resultMap.put(RESULT_MSG, "");
		resultMap.put(ERROR_CODE, "");
	}

	protected void setResultStatus(Object... value) {
		switch (value.length) {
		case 1:
			resultMap.put(RESULT_CODE, value[0]);
			resultMap.put(RESULT_MSG, "");
			resultMap.put(ERROR_CODE, "");
			break;
		case 2:
			resultMap.put(RESULT_CODE, value[0]);
			resultMap.put(RESULT_MSG, value[1]);
			resultMap.put(ERROR_CODE, "");
			break;
		case 3:
			resultMap.put(RESULT_CODE, value[0]);
			resultMap.put(RESULT_MSG, value[1]);
			resultMap.put(ERROR_CODE, value[2]);
			break;
		default:
			break;
		}
	}

	/**
	 * @param d
	 */
	public BaseServiceImpl(D d) {
		this.d = d;
		resultMap = new HashMap<String, Object>();
		resultMap.put(RESULT_CODE, 0);
		resultMap.put(RESULT_MSG, "");
		resultMap.put(ERROR_CODE, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doSave(java.lang.Object)
	 */
	@Transactional
	@Override
	public Map<String, Object> doSave(E entity) {
		resetResultMap();
		try {
			E e = d.save(entity);
			setResultStatus(0, "保存成功");
			resultMap.put(RESULT_ROW, e);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doSave(java.lang.Iterable)
	 */
	@Transactional
	@Override
	public Map<String, Object> doSave(Iterable<E> entities) {
		resetResultMap();
		try {
			List<E> es = d.save(entities);
			setResultStatus(0, "保存成功");
			resultMap.put(RESULT_ROWS, es);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doSelect(java.io.Serializable)
	 */
	@Override
	public Map<String, Object> doSelect(ID id) {
		resetResultMap();
		try {
			E e = d.findOne(id);
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROW, e);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nateiot.core.service.BaseService#doSelect(org.springframework.data
	 * .jpa.domain.Specification)
	 */
	@Override
	public Map<String, Object> doSelect(Specification<E> spec) {
		resetResultMap();
		try {
			E e = d.findOne(spec);
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROW, e);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doSearch()
	 */
	@Override
	public Map<String, Object> doSearch() {
		resetResultMap();
		try {
			List<E> es = d.findAll();
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROWS, es);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doSearch(java.lang.Iterable)
	 */
	@Override
	public Map<String, Object> doSearch(Iterable<ID> ids) {
		resetResultMap();
		try {
			List<E> es = d.findAll(ids);
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROWS, es);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nateiot.core.service.BaseService#doSearch(org.springframework.data
	 * .domain.Pageable)
	 */
	@Override
	public Map<String, Object> doSearch(Pageable pageable) {
		resetResultMap();
		try {
			Page<E> p = d.findAll(pageable);
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_TOTAL, p.getTotalElements());
			resultMap.put(RESULT_ROWS, p.getContent());
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nateiot.core.service.BaseService#doSearch(org.springframework.data
	 * .domain.Sort)
	 */
	@Override
	public Map<String, Object> doSearch(Sort sort) {
		resetResultMap();
		try {
			List<E> es = d.findAll(sort);
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROWS, es);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nateiot.core.service.BaseService#doSearch(org.springframework.data
	 * .jpa.domain.Specification)
	 */
	@Override
	public Map<String, Object> doSearch(Specification<E> spec) {
		resetResultMap();
		try {
			List<E> es = d.findAll(spec);
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROWS, es);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nateiot.core.service.BaseService#doSearch(org.springframework.data
	 * .jpa.domain.Specification, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Map<String, Object> doSearch(Specification<E> spec, Pageable pageable) {
		resetResultMap();
		try {
			Page<E> p = d.findAll(spec, pageable);
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_TOTAL, p.getTotalElements());
			resultMap.put(RESULT_ROWS, p.getContent());
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nateiot.core.service.BaseService#doSearch(org.springframework.data
	 * .jpa.domain.Specification, org.springframework.data.domain.Sort)
	 */
	@Override
	public Map<String, Object> doSearch(Specification<E> spec, Sort sort) {
		resetResultMap();
		try {
			List<E> es = d.findAll(spec, sort);
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROWS, es);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doDelete(java.io.Serializable)
	 */
	@Transactional
	@Override
	public Map<String, Object> doDelete(ID id) {
		resetResultMap();
		try {
			d.delete(id);
			setResultStatus(0, "删除成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doDelete(java.lang.Object)
	 */
	@Transactional
	@Override
	public Map<String, Object> doDelete(E entity) {
		resetResultMap();
		try {
			d.delete(entity);
			setResultStatus(0, "删除成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doDelete(java.lang.Iterable)
	 */
	@Transactional
	@Override
	public Map<String, Object> doDelete(Iterable<E> entities) {
		resetResultMap();
		try {
			d.deleteInBatch(entities);
			setResultStatus(0, "删除成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nateiot.core.service.BaseService#doDeleteAll()
	 */
	@Transactional
	@Override
	public Map<String, Object> doDeleteAll() {
		resetResultMap();
		try {
			d.deleteAllInBatch();
			setResultStatus(0, "删除成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> doSearchBySql(Pageable pageable) {
		return null;
	}

	@Override
	public Map<String, Object> doSearchBySql(Sort sort) {
		return null;
	}

	@Override
	public Map<String, Object> doSearchBySql(
			Map<String, SearchFilter> conditions) {
		return null;
	}

	@Override
	public Map<String, Object> doSearchBySql(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		return null;
	}

	@Override
	public Map<String, Object> doSearchBySql(
			Map<String, SearchFilter> conditions, Sort sort) {
		return null;
	}

	protected List<String[]> getExcelHeaders(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		int firstRowNum = sheet.getFirstRowNum();
		Row row0 = sheet.getRow(firstRowNum);
		List<String[]> titleList = new ArrayList<String[]>();
		String[] strs = null;
		for (int i = 0; i < row0.getLastCellNum(); i++) {
			strs = new String[2];
			strs[0] = "field"+i;
			strs[1] = row0.getCell(i).getStringCellValue();
			titleList.add(strs);
		}
		strs = new String[2];
		strs[0] = "field"+row0.getLastCellNum();
		strs[1] = "校验信息";
		titleList.add(strs);
		return titleList;
	}

	protected List<Map<String, Object>> convertExcelFile(Workbook workbook) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Sheet sheet = workbook.getSheetAt(0);
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();
		List<String[]> titleList = getExcelHeaders(workbook);
		for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			Row row = sheet.getRow(i);
			int firstCellNum = 0;
			int lastCellNum = titleList.size();
			for (int j = firstCellNum; j < lastCellNum; j++) {
				String[] strs = titleList.get(j);
				String tittle = strs[0];
				Cell cell = row.getCell(j);
				if (cell != null) {
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							rowMap.put(tittle, cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (14 == cell.getCellStyle().getDataFormat()) {
								DateTime dateTime = new DateTime(cell.getDateCellValue());
								rowMap.put(tittle, dateTime.toString("yyyy-MM-dd"));
							} else {
								rowMap.put(tittle, cell.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							rowMap.put(tittle, cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							rowMap.put(tittle, cell.getCellFormula());
							break;
						case Cell.CELL_TYPE_BLANK:
							rowMap.put(tittle, cell);
							break;
						default:
							rowMap.put(tittle, cell);
							break;
					}
				} else {
					rowMap.put(tittle, null);
				}
			}
			rowMap.put("field"+lastCellNum, validateExcelFile(row));
			result.add(rowMap);
		}
		return result;
	}

	protected String validateExcelFile(Row row) {
		return "";
	}

	protected Map<String, Object> doExcelImport(List<Map<String, Object>> result) {
		return resultMap;
	}

	protected void setCellValue(Cell cell, Object value) {
		if (value != null) {
			if (value instanceof Number) {
				cell.setCellValue(((Number) value).doubleValue());
			}
			if (value instanceof Boolean) {
				cell.setCellValue((Boolean) value);
			}
			if (value instanceof Date) {
				cell.setCellValue((Date) value);
			}
			if (value instanceof Calendar) {
				cell.setCellValue((Calendar) value);
			}
			if (value instanceof String) {
				cell.setCellValue((String) value);
			}
			if (value instanceof RichTextString) {
				cell.setCellValue((RichTextString) value);
			}
		}
	}

	@Override
	public Map<String, Object> excelImport(ExcelFile excelFile) {
		resetResultMap();
		try {
			Workbook workbook = excelFile.getWorkbook();
			List<Map<String, Object>> result = convertExcelFile(workbook);
			if (excelFile.isPreview()) {
				setResultStatus(0, "预览成功");
				resultMap.put("headers", getExcelHeaders(workbook));
				resultMap.put(RESULT_ROWS, result);
				return resultMap;
			} else {
				return doExcelImport(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "导入时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

}
