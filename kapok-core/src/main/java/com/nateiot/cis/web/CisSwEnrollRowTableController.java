package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwEnrollRowTable;
import com.nateiot.cis.service.CisSwEnrollRowTableService;
import com.nateiot.core.common.web.SearchUtil;

/**
 *  服务办事 -- 事件登记行表
 *  
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/enrollRowTable")
public class CisSwEnrollRowTableController {
	
	@Autowired
	private CisSwEnrollRowTableService cisSwEnrollRowTableService;
	
	/**
	 * 加载试图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/sw/rowTable/rowTable";
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
		return cisSwEnrollRowTableService.doSearch(
				SearchUtil.getSpecification(CisSwEnrollRowTable.class, req),
				SearchUtil.getPageableWithOrderBy(req, "rowTableId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param rowTableId
	 *  @return
	 */
	@RequestMapping(value = "/get/{rowTableId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "rowTableId")Integer rowTableId){
		return cisSwEnrollRowTableService.doSelect(rowTableId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisSwEnrollRowTable
	 *  @return
	 */
//	@RequestMapping(value = "/save")
//	@ResponseBody
//	public Map<String, Object> save(CisSwEnrollRowTable cisSwEnrollRowTable){
//		return cisSwEnrollRowTableService.doSave(cisSwEnrollRowTable);
//	}
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwEnrollRowTable cisSwEnrollRowTable){
		return cisSwEnrollRowTableService.rowTableSave(cisSwEnrollRowTable);
	}
	
	/**
	 * 删除
	 * 
	 *  @param rowTableId
	 *  @return
	 */
	@RequestMapping(value = "/del/{rowTableId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "rowTableId")Integer rowTableId){
		return cisSwEnrollRowTableService.doDelete(rowTableId);
	}
	
	/**
	 * 评价 
	 * 
	 *  @param enrollId, suggestion, backLogId
	 *  @return
	 */
	@RequestMapping(value = "/estimate/{enrollId}/{suggestion}/{backLogId}")
	@ResponseBody
	public Map<String, Object> estimate(
			@RequestParam(value = "enrollId", required = true) Integer enrollId,
			@RequestParam(value = "suggestion", required = false) String remark,
			@RequestParam(value = "backLogId", required = true) Integer backLogId,
			@RequestParam(value = "enrollStatus", required = true) String enrollStatus){
		return cisSwEnrollRowTableService.estimate(enrollId, remark, backLogId, enrollStatus);
	}

}
