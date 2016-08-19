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

import com.nateiot.cis.domain.CisBmGuaranteedLoan;
import com.nateiot.cis.service.CisBmGuaranteedLoanService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 小额担保贷款
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/guaranteedLoan")
public class CisBmGuaranteedLoanController {
	
	@Autowired
	private CisBmGuaranteedLoanService cisBmGuaranteedLoanService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/guarantee/guarantee";
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
		 	/*Map<String ,Object> params = new HashMap<String, Object>();
		 		params.put("delSign_EQ", "N");*/
			return cisBmGuaranteedLoanService.doSearch(
					SearchUtil.getSpecification(CisBmGuaranteedLoan.class, req),
					SearchUtil.getPageableWithOrderBy(req, "guaranteedId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmGuaranteedLoan cisBmGuaranteedLoan) {
		/*if(cisBmGuaranteedLoan!=null){
			cisBmGuaranteedLoan.setDelSign("N");
		}*/
		return cisBmGuaranteedLoanService.doSave(cisBmGuaranteedLoan);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{guaranteedId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "guaranteedId") Integer guaranteedId){
		return cisBmGuaranteedLoanService.doSelect(guaranteedId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{guaranteedId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "guaranteedId") Integer guaranteedId){
		return cisBmGuaranteedLoanService.softDel(guaranteedId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{guaranteedId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "guaranteedId") Integer guaranteedId){
		return cisBmGuaranteedLoanService.doDelete(guaranteedId);
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
			@RequestParam(value = "guaranteedIds") String guaranteedIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(guaranteedIds)){
			list = new ArrayList<Integer>();
			String[] arr = guaranteedIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmGuaranteedLoanService.softDelList(list);
	}

}
