package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisEmHecha;
import com.nateiot.cis.domain.CisEmShencha;
import com.nateiot.cis.domain.CisEmYingjiShijian;
import com.nateiot.cis.domain.CisEmYjsjClmx;
import com.nateiot.cis.domain.CisEmYjsjDoc;
import com.nateiot.cis.service.CisEmHechaService;
import com.nateiot.cis.service.CisEmShenchaService;
import com.nateiot.cis.service.CisEmYingjiShijianService;
import com.nateiot.cis.service.CisEmYjsjClmxService;
import com.nateiot.cis.service.CisEmYjsjDocService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping(value = "/yingjishijian")
public class CisEmYingjiShijianController {
	@Autowired
	private CisEmYingjiShijianService cisEmYingjiShijianService;
	
	@Autowired
	private CisEmShenchaService cisEmShenchaService;
	
	@Autowired
	private CisEmHechaService cisEmHechaService;
	
	@Autowired
	private GxwlSysDocService gxwlSysDocService;
	
	@Autowired
	private CisEmYjsjDocService cisEmYjsjDocService; 
	
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
		return "cis/em/yingjishijian/yingjishijianlist";
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
	 * 調到核查頁面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hecha/page")
	public String toHechaPage() {
		return "cis/em/yingjishijian/hecha";
	}
	
	/**
	 * 調到审查页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shencha/page")
	public String toShenchaPage() {
		return "cis/em/yingjishijian/shencha";
	}
	
	/**
	 * 打开跟进弹窗页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/genjin/page")
	public String toGenjinPage() {
		return "cis/em/yingjishijian/genjin";
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
			return cisEmYingjiShijianService.doSearch(
					SearchUtil.getSpecification(CisEmYingjiShijian.class, req),
					SearchUtil.getPageableWithOrderBy(req, "createTime_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody 
	public Map<String, Object> save(CisEmYingjiShijian cisEmYingjiShijian) {
		return cisEmYingjiShijianService.doSave(cisEmYingjiShijian);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{yingjiShijianId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "yingjiShijianId") Integer yingjiShijianId){
		
		return cisEmYingjiShijianService.doSelect(yingjiShijianId);
	}
	
	/**
	 * 根据应急事件id读取核查信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/hecha/get/{yingjiShijianId}")
	@ResponseBody
	public Map<String, Object> getHechaInfo(
			@PathVariable(value = "yingjiShijianId") Integer yingjiShijianId){
		return cisEmHechaService.findByYingjiShijianId(yingjiShijianId);
	}
	
	/**
	 * 保存核查信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/hecha/save")
	@ResponseBody
	public Map<String, Object> save(CisEmHecha cisEmHecha) {
		return cisEmHechaService.doSave(cisEmHecha);
	}
	
	/**
	 * 根据应急事件id读取审查信息
	 * 
	 * @param 应急事件id
	 * @return
	 */
	@RequestMapping(value = "/shencha/get/{yingjiShijianId}")
	@ResponseBody
	public Map<String, Object> getShenchaInfo(
			@PathVariable(value = "yingjiShijianId") Integer yingjiShijianId){
		return cisEmShenchaService.findByYingjiShijianId(yingjiShijianId);
	}
	
	/**
	 * 保存审查信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/shencha/save")
	@ResponseBody
	public Map<String, Object> save(CisEmShencha cisEmShencha) {
		return cisEmShenchaService.doSave(cisEmShencha);
	}
	
	/**
	 * 保存附件信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/doc/save")
	@ResponseBody
	public Map<String, Object> save(CisEmYjsjDoc cisEmYjsjDoc) {
		return cisEmYjsjDocService.doSave(cisEmYjsjDoc);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{yingjiShijianId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "yingjiShijianId") Integer yingjiShijianId){
		return cisEmYingjiShijianService.doDelete(yingjiShijianId);
	}
	
	/**
	 * 硬删除附件信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doc/del")
	@ResponseBody
	public Map<String, Object> del(@RequestParam(value = "ids") String ids){
		@SuppressWarnings("unchecked")
		List<CisEmYjsjDoc> list = (List<CisEmYjsjDoc>) cisEmYjsjDocService.doSearch(getIds(ids)).get("rows");
		return cisEmYjsjDocService.doDelete(list);
	}
	
	/**
	 * 硬删除附件信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/genjin/del")
	@ResponseBody
	public Map<String, Object> delGenjin(@RequestParam(value = "ids") String ids){
		@SuppressWarnings("unchecked")
		List<CisEmYjsjClmx> list = (List<CisEmYjsjClmx>) cisEmYjsjClmxService.doSearch(getIds(ids)).get("rows");
		return cisEmYjsjClmxService.doDelete(list);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ishechaorshencharen/{yingjiShijianId}")
	@ResponseBody
	public boolean isHechaOrShenchaRen(
			@PathVariable(value = "yingjiShijianId") Integer yingjiShijianId){
		return cisEmYingjiShijianService.isHechaOrShenchaRen(yingjiShijianId);
	}
	
	private List<Integer> getIds(String ids){
		List<Integer> list = new ArrayList<Integer>();
		String strs[] = ids.split(",");
		for(int i = 0; i < strs.length; i ++){
			list.add(Integer.parseInt(strs[i]));
		}
		return list;
	}
	
}
