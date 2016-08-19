package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisGisMunicipalFacilities;
import com.nateiot.cis.service.CisGisMunicipalFacilitiesService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/municipalFacilities")
public class CisGisMunicipalFacilitiesController {
	
	@Autowired
	private CisGisMunicipalFacilitiesService cisGisMunicipalFacilitiesService;
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		  
			return cisGisMunicipalFacilitiesService.doSearch(
					SearchUtil.getSpecification( CisGisMunicipalFacilities.class, req),
					SearchUtil.getPageableWithOrderBy(req, "mfId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisGisMunicipalFacilities model) {
		return cisGisMunicipalFacilitiesService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{mfId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "mfId") Integer mfId){
		return cisGisMunicipalFacilitiesService.doSelect(mfId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{mfId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "mfId") Integer mfId){
		return cisGisMunicipalFacilitiesService.doDelete(mfId);
	}

}
