package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisPaPostDuty;
import com.nateiot.cis.domain.CisPaTrainMembers;
import com.nateiot.cis.service.CisPaPostDutyService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 党务建设 -- 党员设岗定责登记表
 * 
 * @author Guohw
 */
@Controller
@RequestMapping(value = "/postDuty")
public class CisPaPostDutyController {
	
	@Autowired
	private CisPaPostDutyService cisPaPostDutyService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/pa/postDuty/postDuty";
	}
	
	/**
	 * 查找党员(弹出框) 
	 */
	@RequestMapping(value = "/member-select")
	public String memberSelect(){
		return "cis/pa/postDuty/member-select";
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
		return cisPaPostDutyService.doSearch(SearchUtil.getSpecification(CisPaPostDuty.class, req, params),
				SearchUtil.getPageableWithOrderBy(req, "dutyId_desc"));
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/searchBy")
	@ResponseBody
	public Map<String, Object> searchBy(HttpServletRequest req){
		return cisPaPostDutyService.searchBy(
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "dutyId_desc"));
	}
	
	/**
	 *  详细
	 *  
	 *  @param dutyId
	 *  @return
	 */
	@RequestMapping(value = "/get/{dutyId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "dutyId")Integer dutyId){
		return cisPaPostDutyService.doSelect(dutyId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param entity
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisPaPostDuty entity){
		if(entity.getDutyId()==null){
			entity.setDelSign("N");
		}
		return cisPaPostDutyService.doSave(entity);
	}
	
	/**
	 * 软删除
	 * 
	 *  @param dutyId
	 *  @return
	 */
	@RequestMapping(value = "/softDel/{dutyId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "dutyId")Integer dutyId){
		return cisPaPostDutyService.softDel(dutyId);
	}

	/**
	 * 硬删除
	 * 
	 *  @param dutyId
	 *  @return
	 */
	@RequestMapping(value = "/del/{dutyId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "dutyId") Integer dutyId){
		return cisPaPostDutyService.doDelete(dutyId);
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
		return cisPaPostDutyService.findMember(
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "houseHolderId_desc"));
	}
	
}
