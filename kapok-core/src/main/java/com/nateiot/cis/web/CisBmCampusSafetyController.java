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

import com.nateiot.cis.domain.CisBmCampusSafety;
import com.nateiot.cis.service.CisBmCampusSafetyService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 公共安全--校园安全
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/campusSafety")
public class CisBmCampusSafetyController {
	
	@Autowired
	private CisBmCampusSafetyService cisBmCampusSafetyService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/campusSafety/campusSafety";
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
		 	 
			return cisBmCampusSafetyService.doSearch(
					SearchUtil.getSpecification(CisBmCampusSafety.class, req),
					SearchUtil.getPageableWithOrderBy(req, "sacampusId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmCampusSafety cisBmCampusSafety) {
	 
		return cisBmCampusSafetyService.doSave(cisBmCampusSafety);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{sacampusId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "sacampusId") Integer sacampusId){
		
		return cisBmCampusSafetyService.doSelect(sacampusId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{sacampusId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "sacampusId") Integer sacampusId){
		return cisBmCampusSafetyService.softDel(sacampusId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{sacampusId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "sacampusId") Integer sacampusId){
		return cisBmCampusSafetyService.doDelete(sacampusId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param sacampusIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "sacampusIds") String sacampusIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(sacampusIds)){
			list = new ArrayList<Integer>();
			String[] arr = sacampusIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmCampusSafetyService.softDelList(list);
	}

}
