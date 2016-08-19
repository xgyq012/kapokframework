package com.nateiot.cis.repository;

import java.util.Map;

/**
 * 单位信息
 * 
 * @author Administrator
 *
 */
public interface CisBmUnitsDaoPlus {
	
	/**
	 * 按单位的性质，统计指定网格（包括子网格）下的所有单位
	 * @param meshIds 指定的网格及其子网格
	 * @return
	 */
	public Map<String, Object> count(String meshIds);

}
