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

import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.cis.domain.CisBmCommunityMsg;
import com.nateiot.cis.domain.CisBmHouseMsg;
import com.nateiot.cis.service.CisBmBuildingMsgService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping("/buildingmsg")
public class CisBmBuildingMsgController {

	@Autowired
	private CisBmBuildingMsgService cisBmBuildingMsgService;
	
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest req) {
		  
		return "cis/bm/build/list";
	}
	
	@RequestMapping(value = "/selectBuild")
	public String selectBuild(HttpServletRequest req) {
		  
		return "cis/bm/build/selectBuild";
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
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("delSign_NEQ","Y");
		return cisBmBuildingMsgService.doSearch(SearchUtil.getSpecification(CisBmBuildingMsg.class,req,map),
				SearchUtil.getPageableWithOrderBy(req, "buildId_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmBuildingMsg cisBmBuildingMsg) {
		cisBmBuildingMsg.setDelSign("N");
		return cisBmBuildingMsgService.doSave(cisBmBuildingMsg);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{buildId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "buildId") Integer buildId){
		return cisBmBuildingMsgService.doSelect(buildId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{buildId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "buildId") Integer buildId){
		return cisBmBuildingMsgService.doDelete(buildId);
	}
	
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{id}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "id") Integer buildId){
		
		return cisBmBuildingMsgService.softDel(buildId);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
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
		
		return cisBmBuildingMsgService.delList(list);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param householderIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> softDelList(@RequestParam(value = "ids") String ids){
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
		return cisBmBuildingMsgService.softDel(list);
	}
	
	
}
