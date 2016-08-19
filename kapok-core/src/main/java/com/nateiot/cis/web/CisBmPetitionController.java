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

import com.nateiot.cis.domain.CisBmPetitionInfo;
import com.nateiot.cis.service.CisBmPetitionService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 信访信息
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/petition")
public class CisBmPetitionController {
	
	@Autowired
	private CisBmPetitionService cisBmPetitionService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/petition/petition";
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
		  
			return cisBmPetitionService.doSearch(
					SearchUtil.getSpecification(CisBmPetitionInfo.class, req),
					SearchUtil.getPageableWithOrderBy(req, "petitionId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmPetitionInfo cisBmPetitionInfo) {
		 
		return cisBmPetitionService.doSave(cisBmPetitionInfo);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{petitionId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "petitionId") Integer petitionId){
		return cisBmPetitionService.doSelect(petitionId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{petitionId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "petitionId") Integer petitionId){
		return cisBmPetitionService.softDel(petitionId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{petitionId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "petitionId") Integer petitionId){
		return cisBmPetitionService.doDelete(petitionId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param petitionIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "petitionIds") String petitionIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(petitionIds)){
			list = new ArrayList<Integer>();
			String[] arr = petitionIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmPetitionService.softDelList(list);
	}

}
