package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmCommunityMsg;
import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.cis.service.CisBmCommunityMsgService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/community")
public class CisBmCommunityMsgController {


	@Autowired
	private CisBmCommunityMsgService cisBmCommunityMsgService;
	
	@RequestMapping(value = "/list")
	public String toList() {
		
		return "cis/bm/communityInfo/list";
	}
	
	/**
	 * 选择小区
	 * @return
	 */
	@RequestMapping(value = "/selectCommunity")
	public String selectCommunity() {
		
		return "cis/bm/communityInfo/selectCommunity";
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
			map.put("delSign_EQ","N");
			Specification<CisBmCommunityMsg> s = SearchUtil.getSpecification(CisBmCommunityMsg.class, req , map);
			return cisBmCommunityMsgService.doSearch(s,SearchUtil.getPageableWithOrderBy(req, "comId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmCommunityMsg model) {
		model.setDelSign("N");
		return cisBmCommunityMsgService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{comId}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable(value = "comId") Integer comId){
		return cisBmCommunityMsgService.doSelect(comId);
	}
 
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{comId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "comId") Integer comId){
		return cisBmCommunityMsgService.doDelete(comId);
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
		
		return cisBmCommunityMsgService.softDel(id);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param householderIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
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
		return cisBmCommunityMsgService.softDel(list);
	}
	
}
