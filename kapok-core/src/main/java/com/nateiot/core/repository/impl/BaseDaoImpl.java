package com.nateiot.core.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.repository.BaseDaoPlus;
import com.nateiot.core.repository.impl.Field.DataType;

/**
 * @author Will WM. Zhang
 * 
 */
public class BaseDaoImpl implements BaseDaoPlus {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<Map<String, Object>> selectBySql(String sqlString) {
		return selectBySql(sqlString, null, null, null, null);
	}
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions) {
		return selectBySql(sqlString, conditions, null, null, null);
	}
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy) {
		return selectBySql(sqlString, conditions, groupBy, null, null);
	}
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, Sort sort) {
		return selectBySql(sqlString, conditions, null, sort, null);
	}		
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, String groupBy) {
		return selectBySql(sqlString, null, groupBy, null, null);
	}	
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, String groupBy, Sort sort) {
		return selectBySql(sqlString, null, groupBy, sort, null);
	}

	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Sort sort) {
		return selectBySql(sqlString, null, null, sort, null);
	}
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Sort sort) {
		return selectBySql(sqlString, conditions, groupBy, sort, null);
	}	
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, ResultFields resultFields) {
		return selectBySql(sqlString, null, null, null, resultFields);
	}
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, ResultFields resultFields) {
		return selectBySql(sqlString, conditions, null, null, resultFields);
	}
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, ResultFields resultFields) {
		return selectBySql(sqlString, conditions, groupBy, null, resultFields);
	}
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, Sort sort, ResultFields resultFields) {
		return selectBySql(sqlString, conditions, null, sort, resultFields);
	}		
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, String groupBy, ResultFields resultFields) {
		return selectBySql(sqlString, null, groupBy, null, resultFields);
	}	
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, String groupBy, Sort sort, ResultFields resultFields) {
		return selectBySql(sqlString, null, groupBy, sort, resultFields);
	}

	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Sort sort, ResultFields resultFields) {
		return selectBySql(sqlString, null, null, sort, resultFields);
	}
	
	@Override
	public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Sort sort, ResultFields resultFields) {
		Query query = entityManager.createNativeQuery(addConditionSql(sqlString, conditions, "and", groupBy));
		@SuppressWarnings("rawtypes")
		List rows = query.getResultList();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		if (resultFields != null) {
			for (Object row : rows) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				rowConvertToMap(resultMap, row, resultFields.getFields());
				resultList.add(resultMap);
			}
		} else {
			for (Object row : rows) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				rowConvertToMap(resultMap, row, getFields(sqlString));
				resultList.add(resultMap);
			}
		}
		return resultList;
	}
	
	
	/* ******************** */
	

	@Override
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Pageable pageable) {
		return selectBySqlPageable(sqlString, null, null, pageable, null);
	}

	@Override
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, Pageable pageable) {
		return selectBySqlPageable(sqlString, conditions, null, pageable, null);
	}	
	
	@Override
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, String groupBy, Pageable pageable) {
		return selectBySqlPageable(sqlString, null, groupBy, pageable, null);
	}

	@Override
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable) {
		return selectBySqlPageable(sqlString, conditions, groupBy, pageable, null);
	}
	
	@Override
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Pageable pageable, ResultFields resultFields) {
		return selectBySqlPageable(sqlString, null, null, pageable, resultFields);
	}

	@Override
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, Pageable pageable, ResultFields resultFields) {
		return selectBySqlPageable(sqlString, conditions, null, pageable, resultFields);
	}	
	
	@Override
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, String groupBy, Pageable pageable, ResultFields resultFields) {
		return selectBySqlPageable(sqlString, null, groupBy, pageable, resultFields);
	}

	@Override
	public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable, ResultFields resultFields) {
		String sqlStr = addConditionSql(sqlString, conditions, "and", groupBy);
		int total = getResultTotal(sqlStr);
		
		if (StringUtils.startsWith(sqlStr, "COUNT")) {
			sqlStr = StringUtils.substring(sqlStr, 5);
		}
			
		Query query = entityManager.createNativeQuery(addPageableSql(pageable, sqlStr));
		@SuppressWarnings("rawtypes")
		List rows = query.getResultList();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		if (resultFields != null) {
			for (Object row : rows) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				rowConvertToMap(resultMap, row, resultFields.getFields());
				resultList.add(resultMap);
			}
		} else {
			for (Object row : rows) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if (StringUtils.startsWith(sqlString, "COUNT")) {
					sqlString = StringUtils.substring(sqlString, 5);
				}
				rowConvertToMap(resultMap, row, getFields(sqlString));
				resultList.add(resultMap);
			}
		}
		
		return new PageImpl<Map<String, Object>>(resultList, pageable, total);
	}
	
	
	/* ******************** */
	
	
	@Override
	public Map<String, Object> selectOneBySql(String sqlString) {
		return selectOneBySql(sqlString, null, null, null);
	}
	
	@Override
	public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions) {
		return selectOneBySql(sqlString, conditions, null, null);
	}
	
	@Override
	public Map<String, Object> selectOneBySql(String sqlString, String groupBy) {
		return selectOneBySql(sqlString, null, groupBy, null);
	}	
	
	@Override
	public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy) {
		return selectOneBySql(sqlString, conditions, groupBy, null);
	}
	
	@Override
	public Map<String, Object> selectOneBySql(String sqlString, ResultFields resultFields) {
		return selectOneBySql(sqlString, null, null, resultFields);
	}
	
	@Override
	public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions, ResultFields resultFields) {
		return selectOneBySql(sqlString, conditions, null, resultFields);
	}	
	
	@Override
	public Map<String, Object> selectOneBySql(String sqlString, String groupBy, ResultFields resultFields) {
		return selectOneBySql(sqlString, null, groupBy, resultFields);
	}
	
	@Override
	public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, ResultFields resultFields) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Query query = entityManager.createNativeQuery(addConditionSql(sqlString, conditions, "and", groupBy));
			Object row = query.getSingleResult();
			if (resultFields != null) {
				rowConvertToMap(resultMap, row, resultFields.getFields());
			} else {
				rowConvertToMap(resultMap, row, getFields(sqlString));
			}
		} catch (NoResultException e) {
			
		}
		return resultMap;
	}	

	private String addConditionSql(String sqlString, Map<String, SearchFilter> conditions, String conditionsConnector, String groupBy) {
		sqlString = StringUtils.defaultIfBlank(sqlString, StringUtils.EMPTY);
		if (conditions != null) {
			Collection<SearchFilter> c = conditions.values();
			Iterator<SearchFilter> i = c.iterator();
			StringBuffer conditionStr = new StringBuffer();
			while (i.hasNext()) {
				SearchFilter searchFilter = i.next();
				String value = "";
				switch(searchFilter.operator) {
					case EQ:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " = " + searchFilter.value);
						break;
					case NEQ:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " <> " + searchFilter.value);
						break;
					case LIKE:
						value = searchFilter.value.toString().replaceAll("_", "|_").replaceAll("%", "|%");
						value = value.substring(1, value.length()-1);
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " like '%" + value + "%' escape '|'");
						break;
					case STARTWITH:
						value = searchFilter.value.toString().replaceAll("_", "|_").replaceAll("%", "|%");
						value = value.substring(1, value.length()-1);
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " like " + value + "%' escape '|'");
						break;
					case ENDWITH:
						value = searchFilter.value.toString().replaceAll("_", "|_").replaceAll("%", "|%");
						value = value.substring(1, value.length()-1);
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " like '%" + value + "' escape '|'");
						break;
					case GT:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " > " + searchFilter.value);
						break;
					case LT:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " < " + searchFilter.value);
						break;
					case GTE:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " >= " + searchFilter.value);
						break;
					case LTE:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " <= " + searchFilter.value);
						break;
					case IN:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " in (" + searchFilter.value + ")");
						break;
					case NIN:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " not in (" + searchFilter.value + ")");
						break;
					case ISN:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " is null");
						break;
					case ISNN:
						conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " is not null");
						break;
				}
			}
			sqlString = sqlString.concat(conditionStr.toString());
		}
		if (!StringUtils.isEmpty(groupBy)) {
			sqlString = sqlString.concat(" group by ").concat(groupBy);
		}
		return sqlString;
	}
	
	private String addSortSql(Sort sort, String sqlString) {
		if (sort != null) {
			Iterator<Order> orders = sort.iterator();
			StringBuffer orderByStr = new StringBuffer();
			while (orders.hasNext()) {
				Order order = orders.next();
				String property = order.getProperty();
				String direction = order.getDirection().name();
				orderByStr.append(", " + property + " " + direction);
			}
			sqlString = sqlString + " order by" + orderByStr.toString().substring(1);
		}
		return sqlString;
	}
	
	private String addPageableSql(Pageable pageable, String sqlString) {
		if (pageable != null) {
			int page = pageable.getPageNumber();
			int rows = pageable.getPageSize();
			sqlString = addSortSql(pageable.getSort(), sqlString) + " limit " + (page * rows) + ", " + rows;
		} else {
			int page = 1;
			int rows = 10;
			sqlString = sqlString + " limit " + (page * rows) + ", " + rows;
		}
		return sqlString;
	}
	
	private int getResultTotal(String sqlString) {
		
		if (StringUtils.startsWith(sqlString, "COUNT")) {
			
			sqlString = StringUtils.substring(sqlString, 5);
			
			List<KeyValue> keyValues = getKeyValues(sqlString);
			StringBuffer strBuf = getSFString(keyValues);
			
			Integer index = check(0, strBuf);
			Integer start = 0;
			Integer end = 0;
			if (index != -1) {
				start = keyValues.get(0).getKey() + 6;
				end = keyValues.get(index).getKey();
			}
			
			StringBuffer newSqlString = new StringBuffer(sqlString);
			newSqlString.replace(start, end, " COUNT(1) ");
			
			Query query = entityManager.createNativeQuery(newSqlString.toString());
			Object row = query.getSingleResult();
			return ((BigInteger)row).intValue();
		} else {
			Query query = entityManager.createNativeQuery(sqlString);
			return query.getResultList().size();
		}
	}
	
	private void rowConvertToMap(Map<String, Object> resultMap, Object row, String[] fields) {
		if (fields.length > 1) {
			Object[] objs = (Object[]) row;
			for (int i = 0; i < objs.length; i++) {
				resultMap.put(fields[i], objs[i]);
			}
		} else {
			resultMap.put(fields[0], row);
		}
	}	
	
	private void rowConvertToMap(Map<String, Object> resultMap, Object row, List<Field> fields) {
		if (fields.size() > 1) {
			Object[] objs = (Object[]) row;
			for (int i = 0; i < objs.length; i++) {
				String property = fields.get(i).getProperty();
				DataType dataType = fields.get(i).getDataType();
				String dataFormat = fields.get(i).getDataFormat();
				resultMap.put(property, convert(objs[i], dataType, dataFormat));
			}
		} else {
			String property = fields.get(0).getProperty();
			DataType dataType = fields.get(0).getDataType();
			String dataFormat = fields.get(0).getDataFormat();
			resultMap.put(property, convert(row, dataType, dataFormat));
		}
	}
	
	private Object convert(Object value, DataType dataType, String dataFormat) {
		Object result = null;
		switch (dataType) {
		case Date :
			if (dataFormat == null) {
				result = value == null ? null : new DateTime(value).toString("yyyy-MM-dd");
			} else {
				result = value == null ? null : new DateTime(value).toString(dataFormat);
			}
			break;
		case Timestamp :
			if (dataFormat == null) {
				result = value == null ? null : new DateTime(value).toString("yyyy-MM-dd HH:mm:ss");
			} else {
				result = value == null ? null : new DateTime(value).toString(dataFormat);
			}
			break;							
		default:
			result = value;
			break;
		}
		return result;
	}
	
	private String[] getFields(String sqlString) {
		int fromIndex = StringUtils.indexOfIgnoreCase(sqlString, "FROM");
		String fieldString = StringUtils.substring(sqlString, 6, fromIndex - 1);
		fieldString = StringUtils.trimToEmpty(fieldString);
		String[] fields = StringUtils.split(fieldString, ",");
		for (int i = 0; i < fields.length; i++) {
			fields[i] = checkAs(fields[i]);
		}
		return fields;
	}

	private String checkAs(String field) {
		String[] str = StringUtils.split(field);
		return str[str.length - 1];
	}

	private List<KeyValue> getKeyValues(String sqlString) {
		List<KeyValue> keyValues = new LinkedList<KeyValue>();
		int i = 0;
		while (true) {
			i = StringUtils.indexOfIgnoreCase(sqlString, "SELECT", i);
			if (i != -1) {
				KeyValue keyValue = new KeyValue();
				keyValue.setKey(i);
				keyValue.setValue("S");
				keyValues.add(keyValue);
				i++;
			} else {
				break;
			}
		}
		i = 0;
		while (true) {
			i = StringUtils.indexOfIgnoreCase(sqlString, "FROM", i);
			if (i != -1) {
				KeyValue keyValue = new KeyValue();
				keyValue.setKey(i);
				keyValue.setValue("F");
				keyValues.add(keyValue);
				i++;
			} else {
				break;
			}
		}
		Collections.sort(keyValues, new Comparator<KeyValue>(){
			@Override
			public int compare(KeyValue o1, KeyValue o2) {
				return o1.getKey()-o2.getKey();
			}
		});
		return keyValues;
	}
	
	private StringBuffer getSFString(List<KeyValue> keyValues) {
		StringBuffer strBuf = new StringBuffer();
		for (KeyValue keyValue : keyValues) {
			strBuf.append(keyValue.getValue());
		}
		return strBuf;
	}
	
	private Integer check(int sPos, StringBuffer strBuf) {
		int tFpos = strBuf.indexOf("F", sPos);
		int tSpos = strBuf.lastIndexOf("S", tFpos);
		while (tSpos != sPos) {
			try {
				strBuf.setCharAt(tSpos, 'X');
				strBuf.setCharAt(tFpos, 'X');
				tFpos = strBuf.indexOf("F", tFpos);
				tSpos = strBuf.lastIndexOf("S", tFpos);
			} catch (StringIndexOutOfBoundsException e) {
				return -1;
			}
		}
		return tFpos;
	}
	
}
