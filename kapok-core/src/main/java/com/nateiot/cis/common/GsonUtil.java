package com.nateiot.cis.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class GsonUtil {
	
	private static  Gson  gson = new Gson();
 
	/**

     * 将传入的json字符串解析为List集合

     * @param jsonStr

     * @return

     */

    public static List<?> jsonToList(String jsonStr) {

        List<?> ObjectList = null;
        
        if(StringUtils.isNotBlank(jsonStr)){
        	 java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>() {}.getType();
             ObjectList = gson.fromJson(jsonStr, type);
        }

        return ObjectList;

    }
    
    /**

     * 将传入的json字符串解析为List集合

     * @param jsonArray

     * @return

     */

    public static  List<?> jsonToList(JsonArray jsonArray) {

        List<?> ObjectList = null;
        
        if(jsonArray!=null){

            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<LinkedList<?>>() {}.getType();

            ObjectList = gson.fromJson(jsonArray, type);

        }
        
        return ObjectList;

    }
    
    public static List<Map<String,Object>> jsonToListMap(JsonArray jsonArray) {

        List<Map<String,Object>> ObjectList = null;
        
        if(jsonArray!=null){

            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<String,Object>>() {}.getType();

            ObjectList = gson.fromJson(jsonArray, type);

        }
        
        return ObjectList;

    }
    
    /**
     * 把map 转换为json
     * @param map
     * @return
     */
	public static <T> String mapToJson(Map<String, T> map) {
		
		return  gson.toJson(map);
	}
	
	  /**
     * 把list 转换为json
     * @param map
     * @return
     */
	public static <T> String listToJson(List<T> list) {
		
		return  gson.toJson(list);
	}
	
}
