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

import com.nateiot.cis.domain.CisBmHospital;
import com.nateiot.cis.service.CisBmHospitalService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 医院信息
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/hospital")
public class CisBmHospitalController {
	
	@Autowired
	private CisBmHospitalService cisBmHospitalService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/hospital/list";
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
		  
			return cisBmHospitalService.doSearch(
					SearchUtil.getSpecification(CisBmHospital.class, req),
					SearchUtil.getPageableWithOrderBy(req, "hospitalId_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmHospital cisBmHospital) {
		return cisBmHospitalService.doSave(cisBmHospital);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{hospitalId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "hospitalId") Integer hospitalId){
		return cisBmHospitalService.doSelect(hospitalId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{hospitalId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "hospitalId") Integer hospitalId){
		return cisBmHospitalService.softDel(hospitalId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{hospitalId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "hospitalId") Integer hospitalId){
		return cisBmHospitalService.doDelete(hospitalId);
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
		
		return cisBmHospitalService.delList(list);
	}

}
