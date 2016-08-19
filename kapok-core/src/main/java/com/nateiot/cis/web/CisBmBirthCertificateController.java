package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmBirthCertificate;
import com.nateiot.cis.service.CisBmBirthCertificateService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/birthCertificate")
public class CisBmBirthCertificateController {
	
	@Autowired
	private CisBmBirthCertificateService cisBmBirthCertificateService;
	
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/birthCertificate/list";
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
		  
			return cisBmBirthCertificateService.doSearch(
					SearchUtil.getSpecification( CisBmBirthCertificate.class, req),
					SearchUtil.getPageableWithOrderBy(req, "birthId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisBmBirthCertificate model) {
		return cisBmBirthCertificateService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{ocId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "ocId") Integer ocId){
		return cisBmBirthCertificateService.doSelect(ocId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{ocId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "ocId") Integer ocId){
		return cisBmBirthCertificateService.doDelete(ocId);
	}
	
	
	/**
	 * 硬批量删除记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delList(@RequestParam(value = "ids") String ids){
		
		List<Integer> list = null;
		if(StringUtils.isNotBlank(ids)){
			list = new ArrayList<Integer>()  ;
			String[] arry = ids.split(",");
			for (String id : arry) {
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		
		return cisBmBirthCertificateService.delList(list);
	}


}
