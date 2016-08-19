package com.nateiot.cis.web;

import java.util.ArrayList;
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

import com.nateiot.cis.domain.CisBmGuildOrganInfo;
import com.nateiot.cis.service.CisBmGuildOrganInfoService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/guildorganinfo")
public class CisBmGuildOrganInfoController {
	

	@Autowired
	private CisBmGuildOrganInfoService cisBmGuildOrganInfoService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/guildorgan/list";
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
		  
			return cisBmGuildOrganInfoService.doSearch(
					SearchUtil.getSpecification(CisBmGuildOrganInfo.class, req),
					SearchUtil.getPageableWithOrderBy(req, "organizationId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmGuildOrganInfo model) {
		return cisBmGuildOrganInfoService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{mid}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "mid") Integer mid){
		return cisBmGuildOrganInfoService.doSelect(mid);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{mid}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "mid") Integer mid){
		return cisBmGuildOrganInfoService.doDelete(mid);
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
		
		return cisBmGuildOrganInfoService.delList(list);
	}

	
}
