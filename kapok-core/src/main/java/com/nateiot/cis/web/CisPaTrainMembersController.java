package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisPaTrainMembers;
import com.nateiot.cis.service.CisPaTrainMembersService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 党务建设 -- 党员培训情况登记表
 * 
 * @author Guohw
 */
@Controller
@RequestMapping(value = "/trainMembers")
public class CisPaTrainMembersController {
	
	@Autowired
	private CisPaTrainMembersService cisPaTrainMembersService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/pa/trainMembers/trainMembers";
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
		return cisPaTrainMembersService.doSearch(
				SearchUtil.getSpecification(CisPaTrainMembers.class, req, params),
				SearchUtil.getPageableWithOrderBy(req, "promiseId_desc"));
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/searchBy")
	@ResponseBody
	public Map<String, Object> searchBy(
			@RequestParam(value="timeGte", defaultValue="", required=false) String timeGte, 
			@RequestParam(value="timeLte", defaultValue="", required=false) String timeLte, 
			HttpServletRequest req){
		return cisPaTrainMembersService.searchBy(
				timeGte, 
				timeLte, 
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "tm.TRAIN_ID_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param trainId
	 *  @return
	 */
	@RequestMapping(value = "/get/{trainId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "trainId")Integer trainId){
		return cisPaTrainMembersService.doSelect(trainId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param entity
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisPaTrainMembers entity){
		if(entity.getTrainId() == null){
			entity.setDelSign("N");
		}
		return cisPaTrainMembersService.doSave(entity);
	} 
	
	/**
	 * 软删除
	 * 
	 *  @param trainId
	 *  @return
	 */
	@RequestMapping(value = "/softDel/{trainId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "trainId")Integer trainId){
		return cisPaTrainMembersService.softDel(trainId);
	}
	
	/**
	 * 硬删除
	 * 
	 *  @param trainId
	 *  @return
	 */
	@RequestMapping(value = "/del/{trainId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "trainId")Integer trainId){
		return cisPaTrainMembersService.doDelete(trainId);
	}

}
