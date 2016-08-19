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

import com.nateiot.cis.domain.CisEmEmePlace;
import com.nateiot.cis.service.CisEmEmePlaceService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 应急管理 -- 避难场所
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/emeplace")
public class CisEmEmePlaceController {
	
	@Autowired
	private CisEmEmePlaceService cisEmEmePlaceService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/em/emeplace/emeplacelist";
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
			return cisEmEmePlaceService.doSearch(
					SearchUtil.getSpecification(CisEmEmePlace.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "emePlaceId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisEmEmePlace cisEmEmePlace) {
		if(cisEmEmePlace != null){
			cisEmEmePlace.setDelSign("N");
		}
		return cisEmEmePlaceService.doSave(cisEmEmePlace);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{emePlaceId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "emePlaceId") Integer emePlaceId){
		return cisEmEmePlaceService.doSelect(emePlaceId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{emePlaced}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "emePlaceId") Integer emePlaceId){
		return cisEmEmePlaceService.doDelete(emePlaceId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param saCampusIds
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
		return cisEmEmePlaceService.softDelList(list);
	}

	/**
	 * 避难场所GIS
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryRefuge")
	@ResponseBody
	public Map<String, Object> queryRefuge(HttpServletRequest req){
		
		return  cisEmEmePlaceService.queryRefuge(SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, ""));
	}
}
