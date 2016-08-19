package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwWaiter;
import com.nateiot.cis.service.CisSwWaiterService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 服务团队人员信息
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping(value = "/waiter")
public class CisSwWaiterController {
	@Autowired
	private CisSwWaiterService cisSwWaiterService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/sw/waiter/waiterlist";
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
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		 	Map<String ,Object> params = new HashMap<String, Object>();
		 		params.put("", "");
			return cisSwWaiterService.doSearch(
					SearchUtil.getSpecification(CisSwWaiter.class, req),
					SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwWaiter cisSwWaiter) {
		return cisSwWaiterService.doSave(cisSwWaiter);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{waiterId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "waiterId") Integer waiterId){
		return cisSwWaiterService.doSelect(waiterId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{waiterId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "waiterId") Integer waiterId){
		return cisSwWaiterService.doDelete(waiterId);
	}

}
