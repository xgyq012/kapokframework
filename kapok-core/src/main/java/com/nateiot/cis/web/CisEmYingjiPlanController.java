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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisEmYingjiPlan;
import com.nateiot.cis.service.CisEmPlanRenyuanService;
import com.nateiot.cis.service.CisEmYingjiPlanService;
import com.nateiot.core.common.web.SearchUtil;
/**
 * 应急预案
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping(value = "/yingjiplan")
public class CisEmYingjiPlanController {
	@Autowired
	private CisEmYingjiPlanService cisEmYingjiPlanService;
	
	@Autowired
	private CisEmPlanRenyuanService cisEmPlanRenyuanService; 
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/em/plan/yingjiplanlist";
	}
	
	/**
	 * 加载选择应急预案视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/selectplan")
	public String toSelectPlanPage() {
		return "cis/em/plan/selectplan";
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
			return cisEmYingjiPlanService.doSearch(
					SearchUtil.getSpecification(CisEmYingjiPlan.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "createTime_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisEmYingjiPlan cisEmYingjiPlan) {
		if(cisEmYingjiPlan.getYingjiPlanId() == null){
			cisEmYingjiPlan.setDelSign("N");
		}
		return cisEmYingjiPlanService.doSave(cisEmYingjiPlan);
	}
	
	
	// 保存应急人员
	@RequestMapping(value = "/saveplanrenyuan", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveMeshUser(String planRenyuanJson) {
		return cisEmPlanRenyuanService.save(planRenyuanJson);
	}
	
	
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{yingjiPlanId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "yingjiPlanId") Integer yingjiPlanId){
		return cisEmYingjiPlanService.doSelect(yingjiPlanId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public Map<String, Object> del(@RequestParam(value = "ids") String ids){
		return cisEmYingjiPlanService.doDelete(getIds(ids));
	}
	
	private List<Integer> getIds(String ids){
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
	    return list;
	}

}
