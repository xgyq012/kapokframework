package com.nateiot.cis.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisCcSms;
import com.nateiot.cis.service.CisCcSmsService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 指挥中心
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping(value = "/sms")
public class CisCcSmsController {
	@Autowired
	private CisCcSmsService cisCcSmsService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/cc/sms/smslist";
	}
	
	/**
	 * 加载视图2
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list2")
	public String list2() {
		return "cis/bm/hospital/hospital-data";
	}
	
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sms/list")
	public String smsList() {
		return "cis/cc/sms/smslist";
	}
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		 	Map<String ,Object> params = new HashMap<String, Object>();
		 	
			return cisCcSmsService.doSearch(
					SearchUtil.getSpecification(CisCcSms.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(CisCcSms cisCcSms) {
		if(1 == cisCcSms.getSendStatus()){
			cisCcSms.setSendTime(new Date());
		}
		return cisCcSmsService.doSave(cisCcSms);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{smsId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "smsId") Integer smsId){
		return cisCcSmsService.doSelect(smsId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{smsId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "smsId") Integer smsId){
		return cisCcSmsService.doDelete(smsId);
	}
}
