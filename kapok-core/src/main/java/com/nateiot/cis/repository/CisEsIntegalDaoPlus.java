package com.nateiot.cis.repository;

import java.util.Map;

/**
 * 考核督办 -- 积分管理
 * 
 * @author Administrator
 *
 */
public interface CisEsIntegalDaoPlus {
	
	/**
	 * 通过积分配置编码，查找分值
	 * 
	 *  @param integalCode
	 *  @return
	 */
	public Map<String, Object> findScoreByIntegalCode(String integalCode);
}
