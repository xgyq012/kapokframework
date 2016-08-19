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

import com.nateiot.cis.domain.CisBmFireControl;
import com.nateiot.cis.service.CisBmFireControlService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 消防信息
 * 
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping(value = "/fireControl")
public class CisBmFireControlController {
	
	@Autowired
	private CisBmFireControlService cisBmFireControlService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/fireControl/fireControl";
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
		 	 
			return cisBmFireControlService.doSearch(
					SearchUtil.getSpecification(CisBmFireControl.class, req),
					SearchUtil.getPageableWithOrderBy(req, "cofireId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmFireControl cisBmFireControl) {
		 
		return cisBmFireControlService.doSave(cisBmFireControl);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{cofireId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "cofireId") Integer cofireId){
		return cisBmFireControlService.doSelect(cofireId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{cofireId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "cofireId") Integer cofireId){
		return cisBmFireControlService.softDel(cofireId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{cofireId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "cofireId") Integer cofireId){
		return cisBmFireControlService.doDelete(cofireId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param cofireIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "cofireIds") String cofireIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(cofireIds)){
			list = new ArrayList<Integer>();
			String[] arr = cofireIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmFireControlService.softDelList(list);
	}

}
