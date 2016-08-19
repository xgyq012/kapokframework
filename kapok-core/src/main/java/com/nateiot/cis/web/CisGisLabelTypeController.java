package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisGisLabelType;
import com.nateiot.cis.service.CisGisLabelTypeService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/labelType")
public class CisGisLabelTypeController {
	
	@Autowired
	private CisGisLabelTypeService cisGisLabelTypeService;
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		  
			return cisGisLabelTypeService.doSearch(
					SearchUtil.getSpecification( CisGisLabelType.class, req),
					SearchUtil.getPageableWithOrderBy(req, "labelId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisGisLabelType model) {
		return cisGisLabelTypeService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{labelId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "labelId") Integer labelId){
		return cisGisLabelTypeService.doSelect(labelId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{labelId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "labelId") Integer labelId){
		return cisGisLabelTypeService.doDelete(labelId);
	}


}
