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

import com.nateiot.cis.domain.CisBmPublicSecurity;
import com.nateiot.cis.service.CisBmPublicSecurityService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 治安信息
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/puSecurity")
public class CisBmPublicSecurityController {
	
	@Autowired
	private CisBmPublicSecurityService cisBmPublicSecurityService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/puSecurity/pusecurity";
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
		 	 
			return cisBmPublicSecurityService.doSearch(
					SearchUtil.getSpecification(CisBmPublicSecurity.class, req),
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
	public Map<String, Object> save(CisBmPublicSecurity cisBmPublicSecurity) {
		 
		return cisBmPublicSecurityService.doSave(cisBmPublicSecurity);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{pusecurityId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "pusecurityId") Integer pusecurityId){
		return cisBmPublicSecurityService.doSelect(pusecurityId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{pusecurityId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "pusecurityId") Integer pusecurityId){
		return cisBmPublicSecurityService.softDel(pusecurityId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{pusecurityId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "pusecurityId") Integer pusecurityId){
		return cisBmPublicSecurityService.doDelete(pusecurityId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param pusecurityIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "pusecurityIds") String pusecurityIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(pusecurityIds)){
			list = new ArrayList<Integer>();
			String[] arr = pusecurityIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmPublicSecurityService.softDelList(list);
	}

}
