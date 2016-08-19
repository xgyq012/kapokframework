package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisEmYjsjClmx;
import com.nateiot.cis.service.CisEmYjsjClmxService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 应急事件处理明细
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping(value = "/yjsjclmx")
public class CisEmYjsjClmxController {
	@Autowired
	private CisEmYjsjClmxService cisEmYjsjClmxService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/hospital/hospital";
	}
	
	/**
	 * 加载视图2
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list2")
	public String list2() {
		return "cis/bm/hospital/hospital-data";
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
			return cisEmYjsjClmxService.doSearch(
					SearchUtil.getSpecification(CisEmYjsjClmx.class, req),
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
	public Map<String, Object> save(CisEmYjsjClmx cisEmYjsjClmx) {
		return cisEmYjsjClmxService.doSave(cisEmYjsjClmx);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{yjsjclmxId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "yjsjclmxId") Integer yjsjclmxId){
		return cisEmYjsjClmxService.doSelect(yjsjclmxId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{yjsjclmxId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "yjsjclmxId") Integer yjsjclmxId){
		return cisEmYjsjClmxService.doDelete(yjsjclmxId);
	}

}
