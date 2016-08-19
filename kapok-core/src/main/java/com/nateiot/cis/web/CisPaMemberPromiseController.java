package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisPaMemberPromise;
import com.nateiot.cis.service.CisPaMemberPromiseService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 党务建设 -- 党员公开承诺活动登记表
 * 
 * @author Guohw
 */
@Controller
@RequestMapping(value = "/memberPromise")
public class CisPaMemberPromiseController {

	@Autowired
	private CisPaMemberPromiseService cisPaMemberPromiseService;
	
	/**
	 * 加载视图
	 * 
	 *  @param 
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/pa/memberPromise/memberPromise";
	}
	
	/**
	 * 查找党员(弹出框)
	 * 
	 * @param
	 */
	@RequestMapping(value = "/member-select")
	public String member(){
		return "cis/pa/memberPromise/member-select";
	}
	
	/**
	 *  查询
	 *  
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delSign_EQ", "N");
		return cisPaMemberPromiseService.doSearch(
				SearchUtil.getSpecification(CisPaMemberPromise.class, req, params),
				SearchUtil.getPageableWithOrderBy(req, "du.MEETING_ID_desc"));
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/searchBy")
	@ResponseBody
	public Map<String, Object> searchBy(
			@RequestParam(value="timeGte", defaultValue="", required=false) String timeGte, 
			@RequestParam(value="timeLte", defaultValue="", required=false) String timeLte, 
			HttpServletRequest req){
		return cisPaMemberPromiseService.searchBy(
				timeGte, 
				timeLte, 
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "mp.PROMISE_ID_DESC"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param promiseId
	 *  @return
	 */
	@RequestMapping(value = "/get/{promiseId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "promiseId")Integer promiseId){
		return cisPaMemberPromiseService.doSelect(promiseId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param entity
	 *  @return 
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisPaMemberPromise entity){
		if(entity.getPromiseId()==null){
			entity.setDelSign("N");
		}
		return cisPaMemberPromiseService.doSave(entity);
	}
	
	/**
	 * 软删除
	 * 
	 *  @param promiseId
	 *  @return
	 */
	@RequestMapping(value = "/softDel/{promiseId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "promiseId")Integer promiseId){
		return cisPaMemberPromiseService.softDel(promiseId);
	}
	
	/**
	 * 硬删除
	 * 
	 *  @param promiseId
	 *  @return
	 */
	@RequestMapping(value = "/del/{promiseId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "promiseId")Integer promiseId){
		return cisPaMemberPromiseService.doDelete(promiseId);
	}
	
	/**
	 * 查找党员
	 * 
	 *  @param 
	 *  @return
	 */
	@RequestMapping(value = "/findMember")
	@ResponseBody
	public Map<String, Object> findMember(HttpServletRequest req){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delSign_EQ", "N");
		return cisPaMemberPromiseService.findMember(
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "householderId_desc"));
	}
	
}
