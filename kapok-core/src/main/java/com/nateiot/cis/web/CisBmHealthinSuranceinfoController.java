package com.nateiot.cis.web;

import com.nateiot.cis.domain.CisBmHealthinSuranceinfo;
import com.nateiot.cis.service.CisBmHealthinSuranceinfoService;
import com.nateiot.core.common.web.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/healthinSuranceinfo")
public class CisBmHealthinSuranceinfoController {
	
	@Autowired
	private CisBmHealthinSuranceinfoService cisBmHealthinSuranceinfoService ;


	@RequestMapping(value = "/list")
	public String tolist(){

		return "cis/bm/healthinSurance/healthinSuranceinfo";
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
			return cisBmHealthinSuranceinfoService.doSearch(
					SearchUtil.getSpecification(CisBmHealthinSuranceinfo.class, req),
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
	public Map<String, Object> save(CisBmHealthinSuranceinfo model) {
		return cisBmHealthinSuranceinfoService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "id") Integer id){
		return cisBmHealthinSuranceinfoService.doSelect(id);
	}


	/**
	 * 详细
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getInfoByHouseHolder/{id}")
	@ResponseBody
	public Map<String, Object> getInfoByHouseHolder(@PathVariable(value = "id") Integer id){

		return cisBmHealthinSuranceinfoService.getCisBmHealthinSuranceMsg(id);
	}
 
	/**
	 * 硬删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "id") Integer id){
		return cisBmHealthinSuranceinfoService.doDelete(id);
	}
	
}
