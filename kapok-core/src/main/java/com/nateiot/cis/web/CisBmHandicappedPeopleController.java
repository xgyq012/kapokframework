package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmHandicappedPeople;
import com.nateiot.cis.service.CisBmHandicappedPeopleService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 残疾人
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping("/handicappedPeople")
public class CisBmHandicappedPeopleController {

	@Autowired
	private CisBmHandicappedPeopleService cisBmHandicappedPeopleService;
	
	
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest req) {
		  
		return "cis/bm/handicappedPeople/handicappedPeople";
	}
	
	/**
	 * 查询
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		
		return cisBmHandicappedPeopleService.getHandicappedPeopleHouseHolder(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmHandicappedPeople model) {
		return cisBmHandicappedPeopleService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "id") Integer id){
		return cisBmHandicappedPeopleService.doSelect(id);
	}
	
 
	/**
	 * 硬删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "id") Integer id){
		return cisBmHandicappedPeopleService.doDelete(id);
	}
	
	
}
