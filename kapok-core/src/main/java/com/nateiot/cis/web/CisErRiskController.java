package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisErRisk;
import com.nateiot.cis.service.CisErRiskService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 项目风险评估
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/risk")
public class CisErRiskController {
	
	@Autowired
	private CisErRiskService cisErRiskService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 *  @return
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/er/risk/risk";
	}
	
	/**
	 * 初评弹出框
	 * 
	 *  @param
	 *  @return
	 */
	@RequestMapping(value = "/assess")
	public String assess(){
		return "cis/er/risk/assess-select";
	}
	
	/**
	 * 复评弹出框
	 * 
	 *  @param
	 *  @return
	 */
	@RequestMapping(value = "/review")
	public String review(){
		return "cis/er/risk/review-select";
	}
	
	/**
	 * 审核弹出框
	 * 
	 *  @param
	 *  @return
	 */
	@RequestMapping(value = "/audit")
	public String audit(){
		return "cis/er/risk/audit-select";
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delSign_EQ", "N");
		return cisErRiskService.doSearch(
				SearchUtil.getSpecification(CisErRisk.class, req, params),
				SearchUtil.getPageableWithOrderBy(req, "riskId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param riskId
	 *  @return
	 */
	@RequestMapping(value = "/get/{riskId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "riskId")Integer riskId){
//		return cisErRiskService.doSelect(riskId);
		return cisErRiskService.riskSelect(riskId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisErRisk
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisErRisk cisErRisk){
//		return cisErRiskService.doSave(cisErRisk);
		return cisErRiskService.riskSave(cisErRisk);
	}
	
	/**
	 * 软删除
	 * 
	 *  @param riskId
	 *  @return
	 */
	@RequestMapping(value = "/softDel/{riskId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "riskId")Integer riskId){
		return cisErRiskService.softDel(riskId);
	}
	
}
