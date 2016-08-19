package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmSqjzdx;
import com.nateiot.cis.service.CisBmSqjzdxService;
import com.nateiot.core.common.web.SearchUtil;
/**
 * 社区矫正对象
 * @author xiaguangjun
 */
@Controller
@RequestMapping("/sqjzdx")
public class CisBmSqjzdxController {
	
	@Autowired
	private CisBmSqjzdxService cisBmSqjzdxService;
	
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest req) {
		  
		return "cis/bm/sqjzdx/sqjzdx";
	}
	
	/**
	 * 查询
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		
		return cisBmSqjzdxService.getSqjzdxList(SearchUtil.getSearchFilters(req),
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
	public Map<String, Object> save(CisBmSqjzdx model) {
		return cisBmSqjzdxService.doSave(model);
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
		return cisBmSqjzdxService.doSelect(id);
	}
	
 
	/**
	 * 硬删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "id") Integer id){
		return cisBmSqjzdxService.doDelete(id);
	}

}
