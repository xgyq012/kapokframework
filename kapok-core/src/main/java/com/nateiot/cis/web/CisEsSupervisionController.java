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

import com.nateiot.cis.domain.CisEsSupervision;
import com.nateiot.cis.service.CisEsSupervisionService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 考核督办 -- 事件督办
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/supervision")
public class CisEsSupervisionController {
	
	@Autowired
	private CisEsSupervisionService cisEsSupervisionService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/es/supervision/supervision";
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
			return cisEsSupervisionService.doSearch(
					SearchUtil.getSpecification(CisEsSupervision.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "supervisionId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisEsSupervision cisEsSupervision) {
		if(cisEsSupervision!=null){
			cisEsSupervision.setDelSign("N");
		}
		return cisEsSupervisionService.doSave(cisEsSupervision);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{supervisionId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "supervisionId") Integer supervisionId){
		return cisEsSupervisionService.doSelect(supervisionId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{supervisionId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "supervisionId") Integer supervisionId){
		return cisEsSupervisionService.softDel(supervisionId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{supervisionId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "supervisionId") Integer supervisionId){
		return cisEsSupervisionService.doDelete(supervisionId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param supervisionIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "supervisionIds") String supervisionIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(supervisionIds)){
			list = new ArrayList<Integer>();
			String[] arr = supervisionIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisEsSupervisionService.softDelList(list);
	}

}
