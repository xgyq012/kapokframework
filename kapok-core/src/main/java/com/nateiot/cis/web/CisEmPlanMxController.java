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

import com.nateiot.cis.domain.CisEmPlanMx;
import com.nateiot.cis.service.CisEmPlanMxService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/planmx")
public class CisEmPlanMxController {
	@Autowired
	private CisEmPlanMxService cisEmPlanMxService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/cc/notice/noticelist";
	}
	
	/**
	 * 加载编辑应急预案明细页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toedit")
	public String toEditPage() {
		return "cis/em/plan/editplanmx";
	}
	
	/**
	 * 加载视图2
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list2")
	public String list2() {
		return "cis/bm/hospital/hospital-data";
	}
	
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sms/list")
	public String smsList() {
		return "cis/cc/sms/smslist";
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
			return cisEmPlanMxService.doSearch(
					SearchUtil.getSpecification(CisEmPlanMx.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(CisEmPlanMx cisEmPlanMx) {
		return cisEmPlanMxService.doSave(cisEmPlanMx);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{planId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get(	@PathVariable(value = "planId") Integer planId){
		return cisEmPlanMxService.findByYingjiPlanId(planId);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getplanmx/{planmxid}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPlanMx(	@PathVariable(value = "planmxid") Integer planmxid){
		return cisEmPlanMxService.doSelect(planmxid);
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
		@SuppressWarnings("unchecked")
		List<CisEmPlanMx> list = (List<CisEmPlanMx>) cisEmPlanMxService.doSearch(getIds(ids)).get("rows");
		return cisEmPlanMxService.doDelete(list);
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
	

	
	/**
	 * 加载消息公告彈窗
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/totanchuang")
	public String toTanchuang() {
		return "cis/cc/notice/tanchuang_noticelist";
	}
	

}
