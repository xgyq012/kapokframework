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

import com.nateiot.cis.domain.CisBmPatrolInfo;
import com.nateiot.cis.service.CisBmPatrolService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 巡逻队信息
 * 
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping(value = "/patrol")
public class CisBmPatrolController {
	
	@Autowired
	private CisBmPatrolService cisBmPatrolService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/patrol/patrol";
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
		 	 
			return cisBmPatrolService.doSearch(
					SearchUtil.getSpecification(CisBmPatrolInfo.class, req),
					SearchUtil.getPageableWithOrderBy(req, "patrolId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmPatrolInfo cisBmPatrolInfo) {
	 
		return cisBmPatrolService.doSave(cisBmPatrolInfo);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{patrolId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "patrolId") Integer patrolId){
		return cisBmPatrolService.doSelect(patrolId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{patrolId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "patrolId") Integer patrolId){
		return cisBmPatrolService.softDel(patrolId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{patrolId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "patrolId") Integer patrolId){
		return cisBmPatrolService.doDelete(patrolId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param patrolIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "patrolIds") String patrolIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(patrolIds)){
			list = new ArrayList<Integer>();
			String[] arr = patrolIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmPatrolService.softDelList(list);
	}

}
