package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisEsOversee;
import com.nateiot.cis.domain.CisSwEventEnroll;
import com.nateiot.cis.domain.CisSwFuwuShijian;
import com.nateiot.cis.service.CisEsOverseeService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 考核督办 -- 事件督办
 * 
 *  @author guohuawen
 */
@Controller
@RequestMapping(value = "/oversee")
public class CisEsOverseeController {
	
	@Autowired
	private CisEsOverseeService cisEsOverseeService;
	
	/**
	 * 加载视图
	 * 
	 *  @param 
	 *  @return
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/es/oversee/oversee";
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		return cisEsOverseeService.enrollSearch(
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "enrollId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param enrollId
	 *  @return
	 */
	@RequestMapping(value = "/get/{enrollId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "enrollId")Integer enrollId){
		return cisEsOverseeService.enrollSelect(enrollId);
	}
	
	/**
	 *  辑短信指令发送给相关人员
	 *  
	 *  @param
	 *  @return
	 */
	@RequestMapping(value = "/sendMessage")
	@ResponseBody
	public Map<String, Object> sendMessage(){
		// TO DO
		return null;
	}
	

}
