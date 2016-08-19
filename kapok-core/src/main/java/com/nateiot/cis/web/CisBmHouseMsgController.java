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

import com.nateiot.cis.domain.CisBmHouseMsg;
import com.nateiot.cis.service.CisBmHouseMsgService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/house")
class CisBmHouseMsgController {
	
	@Autowired
	private CisBmHouseMsgService cisBmHouseMsgService;
	
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest req) {
		  
		return "cis/bm/house/list";
	}
	
	/**
	 * 选择房屋
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/select-house")
	public String selectHouse(HttpServletRequest req) {
		  
		return "cis/bm/house/selectHouse";
	}
	
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public Map<String, Object> searchHouse(HttpServletRequest req) {
		
		return cisBmHouseMsgService.selectHouse(SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, ""));
		
	}
	
	@RequestMapping(value = "/updateHouseLonAndLat")
	@ResponseBody
	public Map<String, Object> updateLonAndLat(@RequestParam("houseId") Integer houseId,@RequestParam("lat") String lat,@RequestParam("lon") String lon){
		
		return cisBmHouseMsgService.updateHouseLonAndLat(houseId, lon, lat);
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
			return cisBmHouseMsgService.doSearch(
					SearchUtil.getSpecification(CisBmHouseMsg.class,req,map),
					SearchUtil.getPageableWithOrderBy(req, "houseId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmHouseMsg model) {
		model.setDelSign("N");
		return cisBmHouseMsgService.doSave(model);
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
		return cisBmHouseMsgService.doSelect(id);
	}
	
	/**
	 * 查询楼栋下是否存在小区
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/existsHouse/{id}")
	@ResponseBody
	public Map<String, Object> existsHouse(@PathVariable(value = "id") Integer buildId){
		return cisBmHouseMsgService.existsHouseByBuildId(buildId) ;
	}
	
	/**
	 * GIS查询
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryHouseByHolder")
	@ResponseBody
	public Map<String, Object> queryHouseByHolder(HttpServletRequest req){
		
		return  cisBmHouseMsgService.queryHouseByHolder(SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, ""));
	}


	/**
	 * GIS 查询phone
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryHouseByHolderLikeArgs")
	@ResponseBody
	public Map<String, Object> queryHouseByHolderLikeArgs(HttpServletRequest req){
	   	String meshIds = req.getParameter("meshIds");
	   	String args = req.getParameter("args");
		return  cisBmHouseMsgService.queryHouseByHolderLikeArgs(SearchUtil.getPageableWithOrderBy(req, ""),args,meshIds);
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
			@PathVariable(value = "id") Integer id){
		
		return cisBmHouseMsgService.softDel(id);
	}
	
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "id") Integer id){
		return cisBmHouseMsgService.doDelete(id);
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
		
		return cisBmHouseMsgService.delList(list);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param householderIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(@RequestParam(value = "ids") String householderIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(householderIds)){
			list = new ArrayList<Integer>()  ;
			String[] arry = householderIds.split(",");
			for (String id : arry) {
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmHouseMsgService.softDel(list);
	}

}
