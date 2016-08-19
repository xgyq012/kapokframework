package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisErProInvest;
import com.nateiot.cis.service.CisErProInvestService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 经济运行 -- 项目招商
 * 
 *  @author guohuawen
 */
@Controller
@RequestMapping(value = "/proInvest")
public class CisErProInvestController {
	
	@Autowired
	private CisErProInvestService cisErProInvestService;
	
	/**
	 * 加载视图
	 * 
	 *  @param 
	 *  @return
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/er/proInvest/proInvest";
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delSign_EQ","N");
		return cisErProInvestService.doSearch(
				SearchUtil.getSpecification(CisErProInvest.class, req, map),
				SearchUtil.getPageableWithOrderBy(req, "proInvestId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param proInvestId
	 *  @return
	 */
	@RequestMapping(value = "/get/{proInvestId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "proInvestId") Integer proInvestId){
		return cisErProInvestService.doSelect(proInvestId);
	}
	
	/**
	 * 保存 
	 * 
	 * @param cisErProInvest
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisErProInvest cisErProInvest){
		if(cisErProInvest != null){
			cisErProInvest.setDelSign("N");
		}
		return cisErProInvestService.doSave(cisErProInvest);
	}
	
	/**
	 * 硬删除
	 * 
	 *  @param proInvestId
	 *  @return
	 */
	@RequestMapping(value = "/del/{proInvestId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "proInvestId") Integer proInvestId){
		return cisErProInvestService.doDelete(proInvestId);
	}
	
	/**
	 * 软删除 
	 * 
	 * @param proInvestId
	 * @return
	 */
	@RequestMapping(value = "/softDel/{proInvestId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "proInvestId") Integer proInvestId){
		return cisErProInvestService.softDel(proInvestId);
	}
	
	

}
