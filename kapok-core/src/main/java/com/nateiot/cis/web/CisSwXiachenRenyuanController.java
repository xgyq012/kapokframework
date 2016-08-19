package com.nateiot.cis.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisCcNotice;
import com.nateiot.cis.domain.CisSwXiachenRenyuan;
import com.nateiot.cis.service.CisCcNoticeService;
import com.nateiot.cis.service.CisSwXiachenRenyuanService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/xiachenrenyuan")
public class CisSwXiachenRenyuanController {
	@Autowired
	private CisSwXiachenRenyuanService cisSwXiachenRenyuanService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/sw/xiachenrenyuan/xiachenrenyuanlist";
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
			return cisSwXiachenRenyuanService.doSearch(
					SearchUtil.getSpecification(CisSwXiachenRenyuan.class, req),
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
	public Map<String, Object> save(CisSwXiachenRenyuan cisSwXiachenRenyuan) {
		return cisSwXiachenRenyuanService.doSave(cisSwXiachenRenyuan);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{xiachenRenyuanId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "xiachenRenyuanId") Integer xiachenRenyuanId){
		return cisSwXiachenRenyuanService.doSelect(xiachenRenyuanId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{xiachenRenyuanId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "xiachenRenyuanId") Integer xiachenRenyuanId){
		return cisSwXiachenRenyuanService.doDelete(xiachenRenyuanId);
	}

}
