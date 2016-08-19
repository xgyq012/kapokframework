package com.nateiot.cis.repository;

import com.nateiot.core.common.persistence.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CisBmHouseholderDaoPlus  {
	
	
	/**
	 * 获取不是户主的人员信息
	 * @param conditions
	 * @param pageable
	 * @return
	 */
	public  Page<Map<String, Object>> getNotHouseHolder(Map<String, SearchFilter> conditions, Pageable pageable);
	
	/**
	 * 统计给定网格（包括子网格）下的人员信息
	 * @param meshIds 用，号分割的网格id字符串
	 * @return
	 */
	public Map<String, Object> getCountResult(String meshIds);


}
