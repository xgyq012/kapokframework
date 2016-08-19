package com.nateiot.core.common.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.data.jpa.domain.Specification;

/**
 * 搜索过滤器
 * 
 * @author Will WM. Zhang
 * 
 */

public class SearchFilter {

	public static final String SEARCH_PREFIX = "q_";

	public enum Operator {
		EQ, NEQ, LIKE, STARTWITH, ENDWITH, GT, LT, GTE, LTE, IN, NIN, ISN, ISNN;
		public static boolean contains(String value) {
			try {
				Operator.valueOf(value.toUpperCase());
			} catch (IllegalArgumentException e) {
				return false;
			}
			return true;
		}
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	public enum Type {
		String, Integer, Long, Float, Double, BigDecimal, BigInteger, Boolean, Date, Timestamp;
		public static Object convert(String str, Type type, Operator operator) {
			Object result = null;
			switch (type) {
				case Integer:
					result = NumberUtils.createInteger(str);
					break;
				case Long:
					result = NumberUtils.createLong(str);
					break;
				case Float:
					result = NumberUtils.createFloat(str);
					break;
				case Double:
					result = NumberUtils.createDouble(str);
					break;
				case BigDecimal:
					result = NumberUtils.createBigDecimal(str);
					break;
				case BigInteger:
					result = NumberUtils.createBigInteger(str);
					break;
				case Boolean:
					result = java.lang.Boolean.valueOf(str);
					break;
				case Date:
					DateTime dt = DateTime.parse(str);
					if (str.length() <= 10 && operator == Operator.LTE) {
						dt = dt.plusDays(1);
						dt = dt.minusMillis(1);
					}
					result = dt.toDate();
					break;
				case Timestamp:
					result = new java.sql.Timestamp(DateTime.parse(str, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis());
					break;
				default:
					result = str;
			}
			return result;
		}
	}

	public static <T> Specification<T> spec(
			final Collection<SearchFilter> filters, final Class<T> clazz, final String conditionsConnector) {
		return new Specification<T>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
				if (CollectionUtils.isNotEmpty(filters)) {

					List<Predicate> predicates = new ArrayList<Predicate>();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName,
						// 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path exp = root;
						for (int i = 0; i < names.length; i++) {
							String name = names[i];
							if (name.startsWith("J")) {
								name = name.substring(1);
								exp = ((From) exp).join(name);
							} else {
								exp = exp.get(name);
							}
						}

						// logic operator
						switch (filter.operator) {
							case EQ:
								predicates.add(builder.equal(exp, filter.value));
								break;
							case NEQ:
								predicates.add(builder.notEqual(exp, filter.value));
								break;
							case LIKE:
								predicates.add(builder.like(exp, "%" + filter.value + "%"));
								break;
							case STARTWITH:
								predicates.add(builder.like(exp, filter.value + "%"));
								break;
							case ENDWITH:
								predicates.add(builder.like(exp, "%" + filter.value));
								break;
							case GT:
								predicates.add(builder.greaterThan(exp, (Comparable) filter.value));
								break;
							case LT:
								predicates.add(builder.lessThan(exp, (Comparable) filter.value));
								break;
							case GTE:
								predicates.add(builder.greaterThanOrEqualTo(exp, (Comparable) filter.value));
								break;
							case LTE:
								predicates.add(builder.lessThanOrEqualTo(exp, (Comparable) filter.value));
								break;
							case IN:
								Object[] objs = StringUtils.split(filter.value.toString(), ",");
								predicates.add(exp.in(objs));
								break;
							case NIN:
//								Object[] objs = StringUtils.split(filter.value.toString(), ",");
//								predicates.add(exp.in(objs));
								break;
							case ISN:
								predicates.add(exp.isNull());
								break;
							case ISNN:
								predicates.add(exp.isNotNull());
								break;
						}
					}

					// 将所有条件用 and 联合起来
					if (predicates.size() > 0) {
						if (StringUtils.equalsIgnoreCase(conditionsConnector, "or")) {
							return builder.or(predicates.toArray(new Predicate[predicates.size()]));
						}
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}

	/**
	 * searchParams中key的格式为FIELDNAME_OPERATOR
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();

		for (Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			// 过滤掉空值
			if (value == null || StringUtils.isEmpty(value.toString())) {
				continue;
			}

			// 拆分filedName与operator
			String filedName = "";
			Operator operator = null;
			Type type = Type.String;
			String[] strs = StringUtils.split(key, "_");
			if (Operator.contains(strs[strs.length-1])) {
				for (int i = 0; i < strs.length-1; i++) {
					filedName += "_" + strs[i];
				}
				operator = Operator.valueOf(strs[strs.length-1]);
			} else {
				for (int i = 0; i < strs.length-2; i++) {
					filedName += "_" + strs[i];
				}
				operator = Operator.valueOf(strs[strs.length-2]);
				type = Type.valueOf(strs[strs.length-1]);
			}
			
			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName.substring(1), operator, Type.convert(value.toString(), type, operator));
			filters.put(key, filter);
		}

		return filters;
	}
	
	/**
	 * searchParams中key的格式为FIELDNAME_OPERATOR
	 */
	public static Map<String, SearchFilter> parseBySql(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();

		for (Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			// 过滤掉空值
			if (value == null || StringUtils.isEmpty(value.toString())) {
				continue;
			}

			// 拆分filedName与operator
			String filedName = "";
			Operator operator = null;
			Type type = Type.String;
			String[] strs = StringUtils.split(key, "_");
			if (Operator.contains(strs[strs.length-1])) {
				for (int i = 0; i < strs.length-1; i++) {
					filedName += "_" + strs[i];
				}
				operator = Operator.valueOf(strs[strs.length-1]);
			} else {
				for (int i = 0; i < strs.length-2; i++) {
					filedName += "_" + strs[i];
				}
				operator = Operator.valueOf(strs[strs.length-2]);
				type = Type.valueOf(strs[strs.length-1]);
			}
			
			SearchFilter filter = null;
			switch (type) {
				case String:
					if ("IN".equalsIgnoreCase(operator.name()) || "NIN".equalsIgnoreCase(operator.name())) {
						filter = new SearchFilter(filedName.substring(1), operator, "'" + StringUtils.join(StringUtils.split(value.toString(), ","), "','") + "'");
					} else {
						filter = new SearchFilter(filedName.substring(1), operator, "'" + value.toString() + "'");
					}
					filters.put(key, filter);
					break;
				case Date:
					DateTime dt = DateTime.parse(value.toString());
					if (value.toString().length() <= 10 && operator == Operator.LTE) {
						dt = dt.plusDays(1);
						dt = dt.minusMillis(1);
					}
					filter = new SearchFilter(filedName.substring(1), operator, "'" + dt.toString("yyyy-MM-dd") + "'");
					filters.put(key, filter);
					break;
				case Timestamp:
					DateTime dt1 = DateTime.parse(value.toString());
					if (value.toString().length() <= 10 && operator == Operator.LTE) {
						dt1 = dt1.plusDays(1);
						dt1 = dt1.minusMillis(1);
					}
					filter = new SearchFilter(filedName.substring(1), operator, "'" + dt1.toString("yyyy-MM-dd HH:mm:ss") + "'");
					filters.put(key, filter);
					break;
				default:
					filter = new SearchFilter(filedName.substring(1), operator, Type.convert(value.toString(), type, operator));
					filters.put(key, filter);
					break;
			}
		}

		return filters;
	}
}
