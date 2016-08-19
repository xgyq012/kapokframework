package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwServiceTeam;
import com.nateiot.cis.service.CisSwServiceTeamService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 服务办事 -- 服务团队
 * 
 * @author Guohw
 */
@Controller
@RequestMapping(value = "/serviceTeam")
public class CisSwServiceTeamController {
	
	@Autowired
	private CisSwServiceTeamService cisSwServiceTeamService; 
	
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/sw/serviceTeam/serviceTeam";
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delSign_EQ","N");
		return cisSwServiceTeamService.doSearch(
				SearchUtil.getSpecification(CisSwServiceTeam.class, req, map),
				SearchUtil.getPageableWithOrderBy(req, "teamId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param teamId
	 *  @return
	 */
	@RequestMapping(value = "/get/{teamId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "teamId")Integer teamId){
		return cisSwServiceTeamService.doSelect(teamId);
//		return cisSwServiceTeamService.teamSelect(teamId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisSwServiceTeam
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwServiceTeam cisSwServiceTeam, HttpServletRequest req){
//		return cisSwServiceTeamService.doSave(cisSwServiceTeam);
		return cisSwServiceTeamService.teamSave(cisSwServiceTeam ,req.getSession()
				.getServletContext().getRealPath(""));
	}
	
	/**
	 * 删除
	 * 
	 *  @param teamId
	 *  @return
	 */
	@RequestMapping(value = "/del/{teamId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "teamId")Integer teamId){
		return cisSwServiceTeamService.doDelete(teamId);
	} 
	

}
