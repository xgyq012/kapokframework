package com.nateiot.cis.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmCqsfssry;
import com.nateiot.cis.service.CisBmCqsfssryService;

/**
 * 长期涉法涉诉人员
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping("/cqsfssry")
public class CisBmCqsfssryController {

	@Autowired
	private CisBmCqsfssryService cisBmCqsfssryService;
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmCqsfssry model) {
		return cisBmCqsfssryService.doSave(model);
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
		return cisBmCqsfssryService.doSelect(id);
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
		return cisBmCqsfssryService.doDelete(id);
	}
	
}
