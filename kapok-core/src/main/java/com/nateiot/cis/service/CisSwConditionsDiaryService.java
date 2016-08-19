package com.nateiot.cis.service;

import java.util.Map;

import com.nateiot.cis.domain.CisSwConditionsDiary;
import com.nateiot.cis.repository.CisSwConditionsDiaryDao;
import com.nateiot.core.service.BaseService;

/**
 * 服务办事 -- 民情日记
 * 
 *  @author Guohw
 */
public interface CisSwConditionsDiaryService 
		extends BaseService<CisSwConditionsDiaryDao, CisSwConditionsDiary, Integer>{
	
	/**
	 *  保存
	 */
	public Map<String, Object> conditionsSave(CisSwConditionsDiary cisSwConditionsDiary);
	
	/**
	 * 判断状态 
	 */
	public Map<String, Object> gain(Integer diaryId); 
	
	/**
	 * 提交 
	 */
//	public Map<String, Object> submit(Integer diaryId, String diaryStatus);
	public Map<String, Object> submit(Integer diaryId );

}
