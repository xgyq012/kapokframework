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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmUnemployment;
import com.nateiot.cis.service.CisBmUnemploymentService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 失业证办理
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/unemployment")
public class CisBmUnemploymentController {
	
	@Autowired
	private CisBmUnemploymentService cisBmUnemploymentService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/unemploy/unemploy";
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
			return cisBmUnemploymentService.doSearch(
					SearchUtil.getSpecification(CisBmUnemployment.class, req),
					SearchUtil.getPageableWithOrderBy(req, "unemploymentId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmUnemployment cisBmUnemployment) {
		
		return cisBmUnemploymentService.doSave(cisBmUnemployment);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{unemploymentId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "unemploymentId") Integer unemploymentId){
		return cisBmUnemploymentService.doSelect(unemploymentId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	/*@RequestMapping(value = "/softDel/{unemploymentId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "unemploymentId") Integer unemploymentId){
		return cisBmUnemploymentService.softDel(unemploymentId);
	}*/
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{unemploymentId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "unemploymentId") Integer unemploymentId){
		return cisBmUnemploymentService.doDelete(unemploymentId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param cultivateIds
	 * @return
	 */
	/*@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "unemploymentIds") String unemploymentIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(unemploymentIds)){
			list = new ArrayList<Integer>();
			String[] arr = unemploymentIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmUnemploymentService.softDelList(list);
	}*/

}
