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

import com.nateiot.cis.domain.CisBmEnterpriseSafety;
import com.nateiot.cis.service.CisBmEnterpriseSafetyService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 企业安全
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/enterpriseSafety")
public class CisBmEnterpriseSafetyController {
	
	@Autowired
	private CisBmEnterpriseSafetyService cisBmEnterpriseSafetyService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/enterpriseSafety/enterpriseSafety";
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
			return cisBmEnterpriseSafetyService.doSearch(
					SearchUtil.getSpecification(CisBmEnterpriseSafety.class, req),
					SearchUtil.getPageableWithOrderBy(req, "saenterpriseId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmEnterpriseSafety cisBmEnterpriseSafety) {
		 
		return cisBmEnterpriseSafetyService.doSave(cisBmEnterpriseSafety);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{saenterpriseId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "saenterpriseId") Integer saenterpriseId){
		return cisBmEnterpriseSafetyService.doSelect(saenterpriseId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{saenterpriseId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "saenterpriseId") Integer saenterpriseId){
		return cisBmEnterpriseSafetyService.softDel(saenterpriseId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{saenterpriseId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "saenterpriseId") Integer saenterpriseId){
		return cisBmEnterpriseSafetyService.doDelete(saenterpriseId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param saEnterpriseIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "saenterpriseIds") String saenterpriseIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(saenterpriseIds)){
			list = new ArrayList<Integer>();
			String[] arr = saenterpriseIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmEnterpriseSafetyService.softDelList(list);
	}

}
