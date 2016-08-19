package com.nateiot.cis.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwFwsjClmx;
import com.nateiot.cis.service.CisSwFwsjClmxService;
import com.nateiot.core.common.web.SearchUtil;
/**
 * 服务事件处理明细
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping("/fwsjclmx")
public class CisSwFwsjClmxController {
	@Autowired
	private CisSwFwsjClmxService cisSwFwsjClmxService;
	
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
			return cisSwFwsjClmxService.doSearch(
					SearchUtil.getSpecification(CisSwFwsjClmx.class, req),
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
	public Map<String, Object> save(CisSwFwsjClmx cisSwFwsjClmx) {
		cisSwFwsjClmx = testAdd();
		return cisSwFwsjClmxService.doSave(cisSwFwsjClmx);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{fwsjClmxId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "fwsjClmxId") Integer fwsjClmxId){
		return cisSwFwsjClmxService.doSelect(fwsjClmxId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{fwsjClmxId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "fwsjClmxId") Integer fwsjClmxId){
		return cisSwFwsjClmxService.doDelete(fwsjClmxId);
	}

	private CisSwFwsjClmx testAdd(){
		CisSwFwsjClmx model = new CisSwFwsjClmx();
		model.setFuwuShijianId(1);
		model.setChuliFangshi(1);
		model.setQianshouStatus(1);
		model.setChuliYijian("我没意见！");
		model.setCreaterId(1);
		model.setCreateTime(new Date());
		return model;
	}
}
