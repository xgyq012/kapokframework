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

import com.nateiot.cis.domain.CisBmSpecial;
import com.nateiot.cis.service.CisBmSpecialService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 特殊行业
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/special")
public class CisBmSpecialController {
	
	@Autowired
	private CisBmSpecialService cisBmSpecialService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/special/list";
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
			return cisBmSpecialService.doSearch(
					SearchUtil.getSpecification(CisBmSpecial.class, req),
					SearchUtil.getPageableWithOrderBy(req, "specialId_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmSpecial cisBmSpecial) {
		return cisBmSpecialService.doSave(cisBmSpecial);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{specialId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "specialId") Integer specialId){
		return cisBmSpecialService.doSelect(specialId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{specialId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "specialId") Integer specialId){
		return cisBmSpecialService.softDel(specialId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{specialId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "specialId") Integer specialId){
		return cisBmSpecialService.doDelete(specialId);
	}

	
	/**
	 * 硬批量删除记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delList(@RequestParam(value = "ids") String ids){
		
		List<Integer> list = null;
		if(StringUtils.isNotBlank(ids)){
			list = new ArrayList<Integer>()  ;
			String[] arry = ids.split(",");
			for (String id : arry) {
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		
		return cisBmSpecialService.delList(list);
	}
	
}
