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

import com.nateiot.cis.domain.CisBmSocialSecurity;
import com.nateiot.cis.service.CisBmSocialSecurityService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 社保信息
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/socialSecurity")
public class CisBmSocialSecurityController {
	
	@Autowired
	private CisBmSocialSecurityService cisBmSocialSecurityService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/socialSecurity/socialSecurity";
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
			return cisBmSocialSecurityService.doSearch(
					SearchUtil.getSpecification(CisBmSocialSecurity.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "socialSecurityId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmSocialSecurity cisBmSocialSecurity) {
		return cisBmSocialSecurityService.doSave(cisBmSocialSecurity);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{socialSecurityId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "SocialSecurityId") Integer socialSecurityId){
		return cisBmSocialSecurityService.doSelect(socialSecurityId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{socialSecurityId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "socialSecurityId") Integer socialSecurityId){
		return cisBmSocialSecurityService.softDel(socialSecurityId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{socialSecurityId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "socialSecurityId") Integer socialSecurityId){
		return cisBmSocialSecurityService.doDelete(socialSecurityId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param socialSecurityIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "socialSecurityIds") String socialSecurityIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(socialSecurityIds)){
			list = new ArrayList<Integer>();
			String[] arr = socialSecurityIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmSocialSecurityService.softDelList(list);
	}

}
