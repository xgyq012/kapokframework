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

import com.nateiot.cis.domain.CisEmYingjiRenyuan;
import com.nateiot.cis.service.CisEmYingjiRenyuanService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 应急人员
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping(value = "/yingjirenyuan")
public class CisEmYingjiRenyuanController {

	@Autowired
	private CisEmYingjiRenyuanService cisEmYingjiRenyuanService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/em/yingjirenyuan/yingjirenyuanlist";
	}
	
	/**
	 * 加载选择应急人员视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/selectyjry")
	public String toSelectYjry() {
		return "cis/em/yingjirenyuan/selectyingjirenyuan";
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
			return cisEmYingjiRenyuanService.doSearch(
					SearchUtil.getSpecification(CisEmYingjiRenyuan.class, req, params),
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
	public Map<String, Object> save(CisEmYingjiRenyuan model) {
		if(model != null){
			model.setDelSign("N");
		}
		return cisEmYingjiRenyuanService.doSave(model);
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
		return cisEmYingjiRenyuanService.doSelect(id);
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
		return cisEmYingjiRenyuanService.doDelete(id);
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
		return cisEmYingjiRenyuanService.softDelList(list);
	}
}
