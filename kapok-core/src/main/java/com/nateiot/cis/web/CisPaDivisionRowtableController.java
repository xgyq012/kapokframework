package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisPaDivisionRowtable;
import com.nateiot.cis.service.CisPaDivisionRowtableService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 党务建设 -- 两委分工行表
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/divisionRow")
public class CisPaDivisionRowtableController {

	@Autowired
	private CisPaDivisionRowtableService cisPaDivisionRowtableService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/pa/divisionRow/divisionRow";
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
		return cisPaDivisionRowtableService.doSearch(
				SearchUtil.getSpecification(CisPaDivisionRowtable.class, req),
				SearchUtil.getPageableWithOrderBy(req, "tableId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param tableId
	 *  @return
	 */
	@RequestMapping(value = "/get/{tableId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "tableId")Integer tableId){
		return cisPaDivisionRowtableService.doSelect(tableId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param entity
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisPaDivisionRowtable entity){
		if(entity.getTableId() == null){
			entity.setDelSign("N");
		}
		return cisPaDivisionRowtableService.doSave(entity);
	}
	
	/**
	 * 删除
	 * 
	 *  @param tableId
	 *  @return
	 */
	@RequestMapping(value = "/del/{tableId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "tableId")Integer tableId){
		return cisPaDivisionRowtableService.doDelete(tableId);
	}
	
}
