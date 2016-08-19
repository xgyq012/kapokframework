package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisPaActivity;
import com.nateiot.cis.service.CisPaActivityService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 党组织活动
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping("/activity")
public class CisPaActivityController {
	@Autowired
	private CisPaActivityService cisPaActivityService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/pa/activity/activity-list";
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
		 	params.put("delSign_EQ", "N");
			return cisPaActivityService.doSearch(
					SearchUtil.getSpecification(CisPaActivity.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "createTime_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Map<String, Object> save(CisPaActivity cisPaActivity) {
		if(cisPaActivity.getActivityId() == null){
			cisPaActivity.setDelSign("N");
		}
		return cisPaActivityService.doSave(cisPaActivity); 
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{activityId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "activityId") Integer activityId){
		return cisPaActivityService.doSelect(activityId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{activityId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "activityId") Integer activityId){
		return cisPaActivityService.doDelete(activityId);
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
			@RequestParam(value = "ids") String ids){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(ids)){
			list = new ArrayList<Integer>();
			String[] arr = ids.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisPaActivityService.softDelList(list);
	}
}
