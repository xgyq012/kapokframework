package com.gdgxwl.base.common;

import com.gdgxwl.base.repository.GxwlSysDictHDao;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.*;

public class DictUtil {
	
	private static GxwlSysDictHDao gxwlSysDictHDao;
	
	private static List<Map<String, Object>> dictTypeList = new ArrayList<Map<String, Object>>();
	
	private static List<Map<String, Object>> dictList = new ArrayList<Map<String, Object>>();

	public DictUtil(GxwlSysDictHDao dictDao) {
		gxwlSysDictHDao = dictDao;
		syncDict();
	}
	
	/**
	 * 根据字典类型和字典名称获取字典编码
	 * 
	 * @param dictTypeCode
	 * @param dictName
	 * @return
	 */
	public static String getDictCode(String dictTypeCode, String dictName) {
		dictTypeCode = StringUtils.defaultString(dictTypeCode);
		dictName = StringUtils.defaultString(dictName);
		for (Map<String, Object> map : DictUtil.dictList) {
			if (dictTypeCode.equals(map.get("dictTypeCode")) && dictName.equals(map.get("dictName"))) {
				return map.get("dictCode").toString();
			}
		}
		return null;
	}
	
	/**
	 * 根据字典类型和字典编码获取字典编码名称
	 * 
	 * @param dictTypeCode
	 * @param dictCode
	 * @return
	 */
	public static String getDictName(String dictTypeCode, String dictCode) {
		dictTypeCode = StringUtils.defaultString(dictTypeCode);
		dictCode = StringUtils.defaultString(dictCode);
		for (Map<String, Object> map : DictUtil.dictList) {
			if (dictTypeCode.equals(map.get("dictTypeCode")) && dictCode.equals(map.get("dictCode"))) {
				return map.get("dictName").toString();
			}
		}
		return null;
	}
	
	/**
	 * 获取整个字典列表
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> getDictList() {
		return DictUtil.dictList;
	}
	
	/**
	 * 根据字典类型获取字典列表
	 * 
	 * @param dictTypeCode
	 * @return
	 */
	public static List<Map<String, Object>> getDictList(String dictTypeCode) {
		dictTypeCode = StringUtils.defaultString(dictTypeCode);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : DictUtil.dictList) {
			if (dictTypeCode.equals(map.get("dictTypeCode"))) {
				result.add(map);
			}
		}
		Collections.sort(result, getComparator());
		return result;
	}

	/**
	 * 根据字典类型获取字典列表，排除excludes指定的字段
	 * 
	 * @param dictTypeCode
	 * @param excludes
	 * @return
	 */
	public static List<Map<String, Object>> getDictList(String dictTypeCode, String excludes) {
		dictTypeCode = StringUtils.defaultString(dictTypeCode);
		excludes = StringUtils.defaultString(excludes);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : DictUtil.dictList) {
			if (dictTypeCode.equals(map.get("dictTypeCode")) && !excludes.contains(map.get("dictCode").toString())) {
				result.add(map);
			}
		}
		Collections.sort(result, getComparator());
		return result;
	}
	
	/**
	 * 获取整个字典类型列表
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> getDictTypeList() {
		return DictUtil.dictTypeList;
	}

	/**
	 * 从数据库同步数据字典
	 */
	public static void syncDict() {
		DictUtil.dictTypeList = gxwlSysDictHDao.getDictTypeList();
		DictUtil.dictList = gxwlSysDictHDao.getDictList();
	}
	
	/**
	 * 建议使用getDictName(String dictTypeCode, String dictCode)代替
	 * 
	 * @param typeValue
	 * @return
	 */
	@Deprecated
	public static String getDictName(String typeValue) {
		String[] strs = StringUtils.split(typeValue,":");
		return DictUtil.getDictName(strs[0], strs[1]);
	}

	private static Comparator<Map<String, Object>> getComparator() {
		return new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String seq1 = o1.get("seq").toString();
				String seq2 = o2.get("seq").toString();
				return seq1.compareTo(seq2);
			}
		};
	}
	
	/**
	 * 将普通表查询得来的集合转换成数据字典
	 * @param list 数据源
	 * @param dictCode  指定某列为code
	 * @param dictName  指定某列为字典的name
	 * @return 生成的字典集合
	 * @throws Exception 指定要获取的字段不正确（实体中没有对应的成员）
	 */
	public static List<Map<String, Object>> parseDictList(List<Object> list, String dictCode, String dictName) throws Exception{
		if(list.isEmpty()){
			return null;
		}
		dictCode = resetName(dictCode);
		dictName = resetName(dictName);
		
		List<Map<String, Object>> dicts = new ArrayList<Map<String,Object>>();
		Iterator<Object> ite = list.iterator();
		
		while (ite.hasNext()) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			Object obj = ite.next();
			Method method = obj.getClass().getMethod(dictName);
			tempMap.put("dictName", method.invoke(obj));
			method = obj.getClass().getMethod(dictCode);
			tempMap.put("dictCode", method.invoke(obj));
			dicts.add(tempMap);
		}
		return dicts;
	}
	
	private static String resetName(String name){
		String first = name.substring(0, 1).toUpperCase();
		String rest = name.substring(1, name.length());
		String newStr = new StringBuffer(first).append(rest).toString(); 
		return "get"+ newStr;
	}
}
