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

import com.nateiot.cis.domain.CisBmEntrepreneurStar;
import com.nateiot.cis.service.CisBmEntrepreneurStarService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 创业之星
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/entrepreneurStar")
public class CisBmEntrepreneurStarController {
	
	@Autowired
	private CisBmEntrepreneurStarService cisBmEntrepreneurStarService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/entrStar/entrStar";
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
		 	/*Map<String ,Object> params = new HashMap<String, Object>();
		 		params.put("delSign_EQ", "N");*/
			return cisBmEntrepreneurStarService.doSearch(
					SearchUtil.getSpecification(CisBmEntrepreneurStar.class, req),
					SearchUtil.getPageableWithOrderBy(req, "entrepreneurStarId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmEntrepreneurStar cisBmEntrepreneurStar) {
		/*if(cisBmEntrepreneurStar!=null){
			cisBmEntrepreneurStar.setDelSign("N");
		}*/
		return cisBmEntrepreneurStarService.doSave(cisBmEntrepreneurStar);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{entrepreneurStarId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "entrepreneurStarId") Integer entrepreneurStarId){
		return cisBmEntrepreneurStarService.doSelect(entrepreneurStarId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{entrepreneurStarId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "entrepreneurStarId") Integer entrepreneurStarId){
		return cisBmEntrepreneurStarService.softDel(entrepreneurStarId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{entrepreneurStarId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "entrepreneurStarId") Integer entrepreneurStarId){
		return cisBmEntrepreneurStarService.doDelete(entrepreneurStarId);
	}

	/**
	 * 软删除记录列表
	 * 
	 * @param entrepreneurStarIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "entrepreneurStarIds") String entrepreneurStarIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(entrepreneurStarIds)){
			list = new ArrayList<Integer>();
			String[] arr = entrepreneurStarIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmEntrepreneurStarService.softDelList(list);
	}

}
