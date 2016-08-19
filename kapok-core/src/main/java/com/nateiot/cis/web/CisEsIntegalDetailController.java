package com.nateiot.cis.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.service.CisEsIntegalDetailService;
import com.nateiot.core.common.web.SearchUtil;

/**
 *  考核督办 -- 积分明细记录
 *  
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/integalDetail")
public class CisEsIntegalDetailController {
	
	@Autowired
	private CisEsIntegalDetailService cisEsIntegalDetailService;
	
	/**
	 * 加载视图 
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/es/integalDetail/integalDetail";
	}
	
	/**
	 * 查询 
	 * 
	 * @param timeGte, timeLte, req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(
			@RequestParam(value="timeGte", defaultValue="", required=false) String timeGte, 
			@RequestParam(value="timeLte", defaultValue="", required=false) String timeLte, 
			HttpServletRequest req){
		return cisEsIntegalDetailService.searchDetail(
				timeGte,
				timeLte, 
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "detailId_desc")
				);
	}
	
	/**
	 *  积分明细记录数据(接口)
	 *  
	 *  @param userId(用户ID), detailType(积分类型), detailTime(计分时间), 
	 *  voucherId(单据ID), voucherType(单据类型), remark(备注)
	 *  
	 *  @return
	 */ 
	@RequestMapping(value = "/scoreDetail/{userId}/{detailType}/{detailTime}/{voucherId}/{voucherType}/{remark}")
	@ResponseBody
	public Map<String, Object> scoreDetail(
			@PathVariable(value = "userId")Integer userId,
			@PathVariable(value = "detailType")String DetailType,
			@PathVariable(value = "detailTime")Date detailTime,
			@PathVariable(value = "voucherId")Integer voucherId,
			@PathVariable(value = "voucherType")String voucherType,
			@PathVariable(value = "remark") String remark){
		return cisEsIntegalDetailService.scoreDetail(
				userId, DetailType, detailTime, voucherId, voucherType, remark);
	}
	
	
}
