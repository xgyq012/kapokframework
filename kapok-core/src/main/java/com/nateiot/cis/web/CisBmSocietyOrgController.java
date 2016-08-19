package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmUnits;
import com.nateiot.cis.service.CisBmUnitsService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 新社会组织
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/societyOrg")
public class CisBmSocietyOrgController {
	
	@Autowired
	private CisBmUnitsService cisBmUnitsService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/societyOrg/societyOrg";
	}
	
	/**
	 * 加载视图2
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list2")
	public String list2() {
		return "cis/bm/societyOrg/societyOrg-data";
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
		 		params.put("isSociety_EQ", "Y");
			return cisBmUnitsService.doSearch(
					SearchUtil.getSpecification(CisBmUnits.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{unitsId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "unitsId") Integer unitsId){
		return cisBmUnitsService.doSelect(unitsId);
	}

}
