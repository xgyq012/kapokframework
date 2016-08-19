package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisbmVolunteer;
import com.nateiot.cis.service.CisbmVolunteerService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/volunteer")
public class CisbmVolunteerController {

	
	@Autowired
	private CisbmVolunteerService cisbmVolunteerService;
	
	/**
	 * 转到服务团队列表
	 */
	@RequestMapping("/list")
	public String list(){
		return "cis/bm/volunteer/list";
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
		  
			return cisbmVolunteerService.doSearch(
					SearchUtil.getSpecification( CisbmVolunteer.class, req),
					SearchUtil.getPageableWithOrderBy(req, "serviceId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save( CisbmVolunteer model) {
		return cisbmVolunteerService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{serviceId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "serviceId") Integer serviceId){
		return cisbmVolunteerService.doSelect(serviceId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{serviceId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "serviceId") Integer serviceId){
		return cisbmVolunteerService.doDelete(serviceId);
	}

	
}
