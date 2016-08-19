package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmSocialOrgan;
import com.nateiot.cis.service.CisBmSocialOrganService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/socialOrgan")
public class CisBmSocialOrganController {
	
	@Autowired
	private CisBmSocialOrganService cisBmSocialOrganService;
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		  
			return cisBmSocialOrganService.doSearch(
					SearchUtil.getSpecification( CisBmSocialOrgan.class, req),
					SearchUtil.getPageableWithOrderBy(req, "organizationId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisBmSocialOrgan model) {
		return cisBmSocialOrganService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{organizationId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "organizationId") Integer organizationId){
		return cisBmSocialOrganService.doSelect(organizationId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{organizationId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "organizationId") Integer organizationId){
		return cisBmSocialOrganService.doDelete(organizationId);
	}

}
