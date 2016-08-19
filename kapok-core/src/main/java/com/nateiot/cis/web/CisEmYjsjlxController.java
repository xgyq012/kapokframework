package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.base.common.DictUtil;
import com.nateiot.cis.domain.CisEmYjsjlx;
import com.nateiot.cis.service.CisEmYjsjlxService;
import com.nateiot.core.common.web.SearchUtil;
import com.nateiot.core.service.BaseService;

/**
 * 应急事件类型
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping(value="/yjsjlx")
public class CisEmYjsjlxController {
	
	@Autowired
	private CisEmYjsjlxService cisEmYjsjlxService;
	
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/em/yjsjlx/yjsjlxlist";
	}
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/layerlist")
	public String layerList() {
		return "cis/em/yjsjlx/selectyjsjlx";
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
		 	params.put("delSign_EQ", "N");
			return cisEmYjsjlxService.doSearch(
					SearchUtil.getSpecification(CisEmYjsjlx.class, req),
					SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	@RequestMapping(value = "/getDicts")
	@ResponseBody
	public List<Map<String, Object>> getDicts(HttpServletRequest req) throws Exception {
		 	Map<String ,Object> params = new HashMap<String, Object>();
		 	params.put("", "");
		 	params = cisEmYjsjlxService.doSearch(
					SearchUtil.getSpecification(CisEmYjsjlx.class, req),
					SearchUtil.getPageableWithOrderBy(req, ""));
		 	
		 	@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) params.get("rows");
		 	List<Map<String, Object>> dicts = DictUtil.parseDictList(list, "yjsjlxId", "leixingName"); 
			Map<String, Object> dict = new HashMap<String, Object>();
			dict.put("dictCode", "0");
			dict.put("dictName", "请选择");
			dicts.add(0, dict);
			return dicts;
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisEmYjsjlx model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			if(model != null){
				model.setDelSign("N");
			}
			resultMap = cisEmYjsjlxService.doSave(model);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(BaseService.RESULT_MSG, "保存出错！");
			resultMap.put(BaseService.RESULT_CODE, -1);
		}
		return resultMap;
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "id") Integer id){
		return cisEmYjsjlxService.doSelect(id);
	}
	
 
	/**
	 * 硬删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "id") Integer id){
		return cisEmYjsjlxService.doDelete(id);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param cultivateIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "ids") String ids){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(ids)){
			list = new ArrayList<Integer>();
			String[] arr = ids.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisEmYjsjlxService.softDelList(list);
	}
}
