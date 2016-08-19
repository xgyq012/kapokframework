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

import com.nateiot.cis.domain.CisPaCommunityDuty;
import com.nateiot.cis.service.CisPaCommunityDutyService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 党务建设 -- 社区党务公开表
 * 
 * @author Guohw
 */
@Controller
@RequestMapping(value = "/communityDuty")
public class CisPaCommunityDutyController {
	
	@Autowired
	private CisPaCommunityDutyService cisPaCommunityDutyService;
	
	/**
	 * 加载视图
	 * 
	 * @param 
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/pa/communityDuty/communityDuty";
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delSign_EQ", "N");
		return cisPaCommunityDutyService.doSearch(
				SearchUtil.getSpecification(CisPaCommunityDuty.class, req, params),
				SearchUtil.getPageableWithOrderBy(req, "du.MEETING_ID_desc"));
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
		return cisPaCommunityDutyService.searchBy(
				timeGte, 
				timeLte, 
				SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, "du.MEETING_ID_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param meetingId
	 *  @return
	 */
	@RequestMapping(value = "/get/{meetingId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "meetingId")Integer meetingId){
		return cisPaCommunityDutyService.doSelect(meetingId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param entity
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisPaCommunityDuty entity){
		if(entity.getMeetingId() == null){
			entity.setDelSign("N");
		}
		return cisPaCommunityDutyService.doSave(entity);
	}
	
	/**
	 * 硬删除
	 * 
	 *  @param meetingId
	 *  @return
	 */
	@RequestMapping(value = "/del/{meetingId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "meetingId")Integer meetingId){
		return cisPaCommunityDutyService.doDelete(meetingId);
	}
	
	/**
	 *  软删除
	 *  
	 *  @param meetingId
	 *  @return
	 */
	@RequestMapping(value = "/softDel/{meetingId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "meetingId")Integer meetingId){
		return cisPaCommunityDutyService.softDel(meetingId);
	}
	

}
