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

import com.nateiot.cis.domain.CisBmDuryroomInfo;
import com.nateiot.cis.service.CisBmDuryroomService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 值班室信息
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/duryroom")
public class CisBmDuryroomController {
	
	@Autowired
	private CisBmDuryroomService cisBmDuryroomService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/duryroom/duryroom";
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
			return cisBmDuryroomService.doSearch(
					SearchUtil.getSpecification(CisBmDuryroomInfo.class, req),
					SearchUtil.getPageableWithOrderBy(req, "duryroomId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmDuryroomInfo cisBmDuryroomInfo) {
		 
		return cisBmDuryroomService.doSave(cisBmDuryroomInfo);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{duryroomId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "duryroomId") Integer duryroomId){
		return cisBmDuryroomService.doSelect(duryroomId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{duryroomId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "duryroomId") Integer duryroomId){
		return cisBmDuryroomService.softDel(duryroomId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{duryroomId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "duryroomId") Integer duryroomId){
		return cisBmDuryroomService.doDelete(duryroomId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param duryroomIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "duryroomIds") String duryroomIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(duryroomIds)){
			list = new ArrayList<Integer>();
			String[] arr = duryroomIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmDuryroomService.softDelList(list);
	}

}
