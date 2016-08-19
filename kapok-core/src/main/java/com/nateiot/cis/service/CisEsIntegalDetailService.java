package com.nateiot.cis.service;

import java.util.Date;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisEsIntegalDetail;
import com.nateiot.cis.repository.CisEsIntegalDetailDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;


/**
 * 考核督办 -- 积分明细管理
 * 
 * @author Guohw
 * 
 */
public interface CisEsIntegalDetailService extends 
		BaseService<CisEsIntegalDetailDao, CisEsIntegalDetail, Integer>{
	
	/**
	 * 查询 
	 */
	public Map<String, Object> searchDetail(String timeGte, 
			String timeLte, Map<String, SearchFilter> conditions, Pageable pageable);
	
	/**
	 *  积分明细记录数据(接口)
	 *  
	 *  @param userId(用户ID), detailType(积分类型), detailTime(计分时间), 
	 *  voucherId(单据ID), voucherType(单据类型), remark(备注)
	 *  
	 *  @return
	 */ 
	public Map<String, Object> scoreDetail(Integer userId, String detailType,
			Date detailTime, Integer voucherId, String voucherType, String remark);

}
