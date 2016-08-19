package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisErRiskStatus;
import com.nateiot.cis.service.CisErRiskStatusService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 项目风险状态
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/riskStatus")
public class CisErRiskStatusController {
	
	@Autowired
	private CisErRiskStatusService cisErRiskStatusService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 *  @return
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/er/riskStatus/riskStatus";
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
		return cisErRiskStatusService.doSearch(
				SearchUtil.getSpecification(CisErRiskStatus.class, req),
				SearchUtil.getPageableWithOrderBy(req, "riskId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param statusId
	 *  @return
	 */
	@RequestMapping(value = "/get/{riskId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "riskId")Integer riskId){
//		return cisErRiskStatusService.statusSelect(riskId);
		return cisErRiskStatusService.doSelect(riskId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisErRiskStatus
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisErRiskStatus cisErRiskStatus){
		return cisErRiskStatusService.statusSave(cisErRiskStatus);
	}
	
	/**
	 *  软删除
	 *  
	 *  @param statusId
	 *  @return
	 */
	@RequestMapping(value = "/del/{statusId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "statusId")Integer statusId){
		return cisErRiskStatusService.doDelete(statusId);
	}
	
	/**
	 * 判断状态
	 * 
	 *  @param riskId
	 *  @return
	 */
	@RequestMapping(value = "/distinguish/{riskId}")
	@ResponseBody
	public Map<String, Object> distinguish(
			@PathVariable(value = "riskId")Integer riskId){
		return cisErRiskStatusService.distinguish(riskId);
	}
	
	/**
	 * 提交不通过 
	 * 
	 *  @param riskId
	 *  @return
	 */
	@RequestMapping(value = "/noPass")
	@ResponseBody
	public Map<String, Object> noPass(CisErRiskStatus cisErRiskStatus){
		return cisErRiskStatusService.noPass(cisErRiskStatus);
	}

}
