package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmMunicipalFacilite;
import com.nateiot.cis.service.CisBmMunicipalFaciliteService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/municipalFacilite")
public class CisBmMunicipalFaciliteController {
	
	
	@Autowired
	private CisBmMunicipalFaciliteService cisBmMunicipalFaciliteService;
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		  
			return cisBmMunicipalFaciliteService.doSearch(
					SearchUtil.getSpecification( CisBmMunicipalFacilite.class, req),
					SearchUtil.getPageableWithOrderBy(req, "facId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisBmMunicipalFacilite model) {
		return cisBmMunicipalFaciliteService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{facId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "facId") Integer facId){
		return cisBmMunicipalFaciliteService.doSelect(facId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{facId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "facId") Integer facId){
		return cisBmMunicipalFaciliteService.doDelete(facId);
	}


}
