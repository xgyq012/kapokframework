package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmOldPeopleH;
import com.nateiot.cis.service.CisBmOldPeopleHService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/oldpeople")
public class CisBmOldPeopleHController {
	
	@Autowired
	private CisBmOldPeopleHService cisBmOldPeopleHService;
	
	@ResponseBody
	@RequestMapping(value="/save")
	public Map<String,Object> saveModel(CisBmOldPeopleH model,HttpServletRequest req){
		
		
		return cisBmOldPeopleHService.doSave(model);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/get/{id}")
	public Map<String,Object> getData(@PathVariable Integer id){
		
		return cisBmOldPeopleHService.doSelect(id);
	}
	
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest req) {
		  
		return "cis/bm/lnrxx/lnrxx";
	}
	
	/**
	 * 查询
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		
		return cisBmOldPeopleHService.getOldPeopleHouseHolder(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, ""));
	}

}
