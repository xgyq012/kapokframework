package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmCommunityMsg;
import com.nateiot.cis.domain.CisSwConditionsDiary;
import com.nateiot.cis.service.CisSwConditionsDiaryService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 服务办事 -- 民情日记
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/conditionsDiary")
public class CisSwConditionsDiaryController {
	
	@Autowired
	private CisSwConditionsDiaryService cisSwConditionsDiaryService;
	
	/**
	 * 加载视图
	 * 
	 *  @param 
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/sw/conditionsDiary/conditionsDiary";
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
		return cisSwConditionsDiaryService.doSearch(
				SearchUtil.getSpecification(CisSwConditionsDiary.class, req, map),
				SearchUtil.getPageableWithOrderBy(req, "diaryId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param diaryId
	 *  @return
	 */
	@RequestMapping(value = "/get/{diaryId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "diaryId")Integer diaryId){
		return cisSwConditionsDiaryService.doSelect(diaryId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisSwConditionsDiary
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwConditionsDiary cisSwConditionsDiary){
//		return cisSwConditionsDiaryService.doSave(cisSwConditionsDiary);
		return cisSwConditionsDiaryService.conditionsSave(cisSwConditionsDiary);
	}
	
	/**
	 * 删除
	 * 
	 *  @param diaryId
	 *  @return
	 */
	@RequestMapping(value = "/del/{diaryId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "diaryId")Integer diaryId){
		return cisSwConditionsDiaryService.doDelete(diaryId);
	}
	
	/**
	 * 判断状态 
	 */
	@RequestMapping(value = "/gain/{diaryId}")
	@ResponseBody
	public Map<String, Object> gain(
			@PathVariable(value = "diaryId")Integer diaryId){
		return cisSwConditionsDiaryService.gain(diaryId);
	}
	
	/**
	 * 提交 
	 */
	@RequestMapping(value = "/submit/{diaryId}")
	@ResponseBody
	public Map<String, Object> submit(
			@PathVariable(value="diaryId")Integer diaryId
//			@RequestParam(value = "diaryStatus", required = true) String diaryStatus){
			){
//		return cisSwConditionsDiaryService.submit(diaryId, diaryStatus);
		return cisSwConditionsDiaryService.submit(diaryId);
	}

}
