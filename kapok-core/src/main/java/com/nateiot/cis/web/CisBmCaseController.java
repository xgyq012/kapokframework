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

import com.nateiot.cis.domain.CisBmCaseInfo;
import com.nateiot.cis.service.CisBmCaseService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 案发情况
 *    
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping(value = "/case")
public class CisBmCaseController {
	
	@Autowired
	private CisBmCaseService cisBmCaseService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/case/case";
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
			return cisBmCaseService.doSearch(
					SearchUtil.getSpecification(CisBmCaseInfo.class, req),
					SearchUtil.getPageableWithOrderBy(req, "caseId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmCaseInfo cisBmCaseInfo) {
	 
		return cisBmCaseService.doSave(cisBmCaseInfo);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{caseId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "caseId") Integer caseId){
		return cisBmCaseService.doSelect(caseId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{caseId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "caseId") Integer caseId){
		return cisBmCaseService.softDel(caseId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{caseId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "caseId") Integer caseId){
		return cisBmCaseService.doDelete(caseId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param caseIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "caseIds") String caseIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(caseIds)){
			list = new ArrayList<Integer>();
			String[] arr = caseIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmCaseService.softDelList(list);
	}

}
