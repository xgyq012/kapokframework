package com.nateiot.cis.web;

import com.nateiot.cis.domain.CisBmSocialinfo;
import com.nateiot.cis.service.CisBmSocialinfoService;
import com.nateiot.core.common.web.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 社保信息
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping(value = "/socialinfo")
public class CisBmSocialinfoController {
	
	@Autowired
	private CisBmSocialinfoService cisBmSocialinfoService;



	@RequestMapping(value = "/list")
	public String tolist(){

			return "cis/bm/socialinfo/socialinfo";
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
			return cisBmSocialinfoService.doSearch(
					SearchUtil.getSpecification(CisBmSocialinfo.class, req),
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
	public Map<String, Object> save(CisBmSocialinfo model) {
		return cisBmSocialinfoService.doSave(model);
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
		return cisBmSocialinfoService.doSelect(id);
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

		return cisBmSocialinfoService.getCisBmSocialinfoByholderIdMsg(id);
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
		return cisBmSocialinfoService.doDelete(id);
	}
	
}
