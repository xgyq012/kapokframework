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

import com.nateiot.cis.domain.CisEsIntegal;
import com.nateiot.cis.service.CisEsIntegalService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 考核督办 -- 积分管理
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/integal")
public class CisEsIntegalController {
	
	@Autowired
	private CisEsIntegalService cisEsIntegalService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/es/integal/integal";
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
//		 	Map<String ,Object> params = new HashMap<String, Object>();
//		 		params.put("delSign_EQ", "N");
			return cisEsIntegalService.doSearch(
					SearchUtil.getSpecification(CisEsIntegal.class, req),
					SearchUtil.getPageableWithOrderBy(req, "integalId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisEsIntegal cisEsIntegal) {
		if(cisEsIntegal!=null){
			cisEsIntegal.setDelSign("N");
		}
		return cisEsIntegalService.doSave(cisEsIntegal);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{integalId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "integalId") Integer integalId){
		return cisEsIntegalService.doSelect(integalId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{integalId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "integalId") Integer integalId){
		return cisEsIntegalService.softDel(integalId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{integalId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "integalId") Integer integalId){
		return cisEsIntegalService.doDelete(integalId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param integalIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "integalIds") String integalIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(integalIds)){
			list = new ArrayList<Integer>();
			String[] arr = integalIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisEsIntegalService.softDelList(list);
	}
	
	/**
	 * 通过积分配置编码，查找分值
	 * 
	 *  @param integalCode
	 *  @return
	 */
	@RequestMapping(value = "/gain/{integalCode}")
	@ResponseBody
	public Integer gain(
			@PathVariable(value = "integalCode")String integalCode){
		return cisEsIntegalService.findScore(integalCode);
	}

}
