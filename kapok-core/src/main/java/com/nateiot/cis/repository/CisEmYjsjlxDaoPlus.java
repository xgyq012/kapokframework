package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

import com.nateiot.core.repository.BaseDaoPlus;

/**
 * 应急事件类型
 * @author xiewenhua
 *
 */
public interface CisEmYjsjlxDaoPlus extends BaseDaoPlus{

	public List<Map<String, Object>> getEmergency();
}
