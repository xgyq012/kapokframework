package com.nateiot.cis.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmDieinfo;
import com.nateiot.cis.service.CisBmDieinfoService;


/**
 * 死亡信息
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping(value = "/dieinfo")
public class CisBmDieinfoController {
	
	@Autowired
	private CisBmDieinfoService cisBmDieinfoService;
	
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmDieinfo model) {
		return cisBmDieinfoService.doSave(model);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "id") Integer id){
		return cisBmDieinfoService.doSelect(id);
	}
	
 
	/**
	 * 硬删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "id") Integer id){
		return cisBmDieinfoService.doDelete(id);
	}
	
}
