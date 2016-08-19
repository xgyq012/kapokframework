package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisGisIcon;
import com.nateiot.cis.service.CisGisIconService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/iconManage")
public class CisGisIconController {
	
	@Autowired
	private CisGisIconService cisGisIconService;
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		  
			return cisGisIconService.doSearch(
					SearchUtil.getSpecification( CisGisIcon.class, req),
					SearchUtil.getPageableWithOrderBy(req, "iconId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisGisIcon model) {
		return cisGisIconService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{iconId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "iconId") Integer iconId){
		return cisGisIconService.doSelect(iconId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{iconId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "iconId") Integer iconId){
		return cisGisIconService.doDelete(iconId);
	}


}
