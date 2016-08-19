package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmOnlyChildCertificate;
import com.nateiot.cis.service.CisBmOnlyChildCertificateService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/childCertificate")
public class CisBmOnlyChildCertificateController {
	
	@Autowired
	private CisBmOnlyChildCertificateService cisBmOnlyChildCertificateService;
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		  
			return cisBmOnlyChildCertificateService.doSearch(
					SearchUtil.getSpecification( CisBmOnlyChildCertificate.class, req),
					SearchUtil.getPageableWithOrderBy(req, "bId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisBmOnlyChildCertificate model) {
		return cisBmOnlyChildCertificateService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{mid}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "mid") Integer mid){
		return cisBmOnlyChildCertificateService.doSelect(mid);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{mid}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "mid") Integer mid){
		return cisBmOnlyChildCertificateService.doDelete(mid);
	}


}
