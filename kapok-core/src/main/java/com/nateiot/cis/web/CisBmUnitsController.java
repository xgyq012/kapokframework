package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

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
 * 单位信息
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/units")
public class CisBmUnitsController {
	
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
		return "cis/bm/units/list";
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
			return cisBmUnitsService.doSearch(
					SearchUtil.getSpecification(CisBmUnits.class, req),
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
	public Map<String, Object> save(CisBmUnits cisBmUnits) {
		return cisBmUnitsService.doSave(cisBmUnits);
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
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{unitsId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "unitsId") Integer unitsId){
		return cisBmUnitsService.softDel(unitsId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{unitsId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "unitsId") Integer unitsId){
		return cisBmUnitsService.doDelete(unitsId);
	}

}
