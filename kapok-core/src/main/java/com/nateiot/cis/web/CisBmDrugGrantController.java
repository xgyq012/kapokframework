package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmDrugGrant;
import com.nateiot.cis.service.CisBmDrugGrantService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/drugGrant")
public class CisBmDrugGrantController {

	
	@Autowired
	private CisBmDrugGrantService cisBmDrugGrantService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/drugGrant/list";
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
		  
			return cisBmDrugGrantService.doSearch(
					SearchUtil.getSpecification( CisBmDrugGrant.class, req),
					SearchUtil.getPageableWithOrderBy(req, "drId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisBmDrugGrant model) {
		return cisBmDrugGrantService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{dId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "dId") Integer dId){
		return cisBmDrugGrantService.doSelect(dId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{dId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "dId") Integer dId){
		return cisBmDrugGrantService.doDelete(dId);
	}
	
}
