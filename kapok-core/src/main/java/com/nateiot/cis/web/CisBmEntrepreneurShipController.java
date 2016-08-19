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

import com.nateiot.cis.domain.CisBmEntrepreneurShip;
import com.nateiot.cis.service.CisBmEntrepreneurShipService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 创业商户
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/entrepreneurShip")
public class CisBmEntrepreneurShipController {
	
	@Autowired
	private CisBmEntrepreneurShipService cisBmEntrepreneurShipService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/entrShip/entrShip";
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
			return cisBmEntrepreneurShipService.doSearch(
					SearchUtil.getSpecification(CisBmEntrepreneurShip.class, req),
					SearchUtil.getPageableWithOrderBy(req, "entrepreneurShipId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmEntrepreneurShip cisBmEntrepreneurShip) {
		/*if(cisBmEntrepreneurShip!=null){
			cisBmEntrepreneurShip.setDelSign("N");
		}*/
		return cisBmEntrepreneurShipService.doSave(cisBmEntrepreneurShip);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{entrepreneurShipId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "entrepreneurShipId") Integer entrepreneurShipId){
		return cisBmEntrepreneurShipService.doSelect(entrepreneurShipId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{entrepreneurShipId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "entrepreneurShipId") Integer entrepreneurShipId){
		return cisBmEntrepreneurShipService.softDel(entrepreneurShipId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{entrepreneurShipId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "entrepreneurShipId") Integer entrepreneurShipId){
		return cisBmEntrepreneurShipService.doDelete(entrepreneurShipId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param cultivateIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "entrepreneurShipIds") String entrepreneurShipIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(entrepreneurShipIds)){
			list = new ArrayList<Integer>();
			String[] arr = entrepreneurShipIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmEntrepreneurShipService.softDelList(list);
	}

}
