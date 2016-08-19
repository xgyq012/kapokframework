package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwMinqingRiji;
import com.nateiot.cis.service.CisSwMinqingRijiService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/minqingriji")
public class CisSwMinqingRijiController {
	@Autowired
	private CisSwMinqingRijiService cisSwMinqingRijiService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/sw/minqingriji/minqingrijilist";
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
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		 	Map<String ,Object> params = new HashMap<String, Object>();
		 	params.put("", "");
		 	
			Specification spec = SearchUtil.getSpecification(CisSwMinqingRiji.class, req);
//		 	Map<String, SearchFilter> map = SearchUtil.getSearchFilters(req);
		 	Pageable page = SearchUtil.getPageableWithOrderBy(req, "");
			return cisSwMinqingRijiService.doSearch(spec , page);
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwMinqingRiji cisSwMinqingRiji) {
		return cisSwMinqingRijiService.doSave(cisSwMinqingRiji);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{minqingRijiId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "minqingRijiId") Integer minqingRijiId){
		return cisSwMinqingRijiService.doSelect(minqingRijiId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{minqingRijiId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "minqingRijiId") Integer minqingRijiId){
		return cisSwMinqingRijiService.doDelete(minqingRijiId);
	}
}
