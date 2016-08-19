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

import com.nateiot.cis.domain.CisBmFoodSafety;
import com.nateiot.cis.service.CisBmFoodSafetyService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 食品安全
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/foodSafety")
public class CisBmFoodSafetyController {
	
	@Autowired
	private CisBmFoodSafetyService cisBmFoodSafetyService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/saFoods/saFoods";
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
		 	 
			return cisBmFoodSafetyService.doSearch(
					SearchUtil.getSpecification(CisBmFoodSafety.class, req),
					SearchUtil.getPageableWithOrderBy(req, "safoodsId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmFoodSafety cisBmFoodSafety) {
		 
		return cisBmFoodSafetyService.doSave(cisBmFoodSafety);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{safoodsId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "safoodsId") Integer safoodsId){
		return cisBmFoodSafetyService.doSelect(safoodsId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{safoodsId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "safoodsId") Integer safoodsId){
		return cisBmFoodSafetyService.softDel(safoodsId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{safoodsId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "safoodsId") Integer safoodsId){
		return cisBmFoodSafetyService.doDelete(safoodsId);
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
			@RequestParam(value = "safoodsIds") String safoodsIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(safoodsIds)){
			list = new ArrayList<Integer>();
			String[] arr = safoodsIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmFoodSafetyService.softDelList(list);
	}

}
