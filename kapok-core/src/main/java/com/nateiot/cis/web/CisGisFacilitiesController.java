package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisGisPublicFacilities;
import com.nateiot.cis.service.CisGisFacilitiesService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 市政设施
 * @author hezhenxian
 *
 */
@Controller
@RequestMapping(value = "/fac")
public class CisGisFacilitiesController {


	@Autowired
	private CisGisFacilitiesService cisGisFacilitiesService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/gis/fac/list";
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
			Map<String,Object> map = new HashMap<String,Object>();
			Specification<CisGisPublicFacilities> s = SearchUtil.getSpecification(CisGisPublicFacilities.class, req , map);
			return cisGisFacilitiesService.doSearch(s,SearchUtil.getPageableWithOrderBy(req, "facilitiesId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisGisPublicFacilities model) {
		return cisGisFacilitiesService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "id") Integer id){
		return cisGisFacilitiesService.doSelect(id);
	}
	
 
	/**
	 * 硬删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "id") Integer id){
		return cisGisFacilitiesService.doDelete(id);
	}
	
	
}
