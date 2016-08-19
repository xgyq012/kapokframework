package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwEventEnroll;
import com.nateiot.cis.domain.CisSwEventType;
import com.nateiot.cis.service.CisSwEventTypeService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 服务办事 -- 事件类型管理
 * 
 * @author Guohw
 */
@Controller
@RequestMapping(value = "/eventType")
public class CisSwEventTypeController {
	
	@Autowired
	private CisSwEventTypeService cisSwEventTypeService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/sw/eventType/eventType";
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		return cisSwEventTypeService.doSearch(
				SearchUtil.getSpecification(CisSwEventType.class, req),
				SearchUtil.getPageableWithOrderBy(req, "typeId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param typeId
	 *  @return
	 */
	@RequestMapping(value = "/get/{typeId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "typeId")Integer typeId){
		return cisSwEventTypeService.doSelect(typeId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisSwEventType
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwEventType cisSwEventType,
			HttpServletRequest req){
		return cisSwEventTypeService.eventSave(cisSwEventType, req.getSession()
				.getServletContext().getRealPath(""));
	}
	
	/**
	 * 删除
	 * 
	 *  @param typeId
	 *  @return
	 */
	@RequestMapping(value = "/del/{typeId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "typeId")Integer typeId){
		return cisSwEventTypeService.doDelete(typeId);
	}
	
	/**
	 * 服务办事工作台列表
	 * 
	 *  @param
	 *  @return
	 */
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest req){
		return cisSwEventTypeService.getData(
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "enrollId_desc"));
	}
	
}
