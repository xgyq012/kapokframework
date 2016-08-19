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

import com.nateiot.cis.domain.CisBmSafetyInfo;
import com.nateiot.cis.service.CisBmSafetyService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 技防信息
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/safety")
public class CisBmSafetyController {
	
	@Autowired
	private CisBmSafetyService cisBmSafetyService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/safety/safety";
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
			return cisBmSafetyService.doSearch(
					SearchUtil.getSpecification(CisBmSafetyInfo.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "safetyId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmSafetyInfo cisBmSafetyInfo) {
		return cisBmSafetyService.doSave(cisBmSafetyInfo);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{safetyId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "safetyId") Integer safetyId){
		return cisBmSafetyService.doSelect(safetyId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{safetyId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "safetyId") Integer safetyId){
		return cisBmSafetyService.softDel(safetyId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{safetyId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "safetyId") Integer safetyId){
		return cisBmSafetyService.doDelete(safetyId);
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
			@RequestParam(value = "safetyIds") String safetyIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(safetyIds)){
			list = new ArrayList<Integer>();
			String[] arr = safetyIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmSafetyService.softDelList(list);
	}

}
