package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisPaDivisionLabor;
import com.nateiot.cis.domain.CisPaDivisionRowtable;
import com.nateiot.cis.service.CisPaDivisionLaborService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 党务建设 -- 两委分工
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/division")
public class CisPaDivisionLaborController {
	
	@Autowired
	private CisPaDivisionLaborService cisPaDivisionLaborService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/pa/division/division";
	}
	
	/**
	 * 查找党员(弹出框)
	 * 
	 * @param
	 */
	@RequestMapping(value = "/division-select")
	public String member(){
		return "cis/pa/division/division-select";
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delSign_EQ", "N");
		return cisPaDivisionLaborService.doSearch(
				SearchUtil.getSpecification(CisPaDivisionLabor.class, req, params),
				SearchUtil.getPageableWithOrderBy(req, "laborId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param laborId
	 *  @return
	 */
	@RequestMapping(value = "/get/{laborId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "laborId")Integer laborId){
		return cisPaDivisionLaborService.doSelect(laborId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param entity
	 *  @return
	 */
	@RequestMapping(value = "/saveEn")
	@ResponseBody
	public Map<String, Object> saveEn(CisPaDivisionLabor entity){
		return cisPaDivisionLaborService.saveEn(entity);
	}
	
	/**
	 * 软删除
	 * 
	 *  @param laborId
	 *  @return
	 */
	@RequestMapping(value = "/softDel/{laborId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "laborId")Integer laborId){
		return cisPaDivisionLaborService.softDel(laborId);
	}
	
	/**
	 * 硬删除
	 * 
	 *  @param laborId
	 *  @return
	 */
	@RequestMapping(value = "/del/{laborId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "laborId")Integer laborId){
		return cisPaDivisionLaborService.doDelete(laborId);
	}
	
	/**
	 * 查找党员
	 * 
	 *  @param 
	 *  @return
	 */
	@RequestMapping(value = "/findMember")
	@ResponseBody
	public Map<String, Object> findMember(HttpServletRequest req){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delSign_EQ", "N");
		return cisPaDivisionLaborService.findMember(
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "householderId_desc"));
	}

}
