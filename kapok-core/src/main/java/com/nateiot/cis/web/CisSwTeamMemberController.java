package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwTeamMember;
import com.nateiot.cis.service.CisSwTeamMemberService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 服务办事 -- 服务团队行表
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/teamMember")
public class CisSwTeamMemberController {
	
	@Autowired
	private CisSwTeamMemberService cisSwTeamMemberService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/sw/teamMember/teamMember";
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
		return cisSwTeamMemberService.doSearch(
				SearchUtil.getSpecification(CisSwTeamMember.class, req),
				SearchUtil.getPageableWithOrderBy(req, "teamMember_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param teamMemberId
	 *  @return
	 */
	@RequestMapping(value = "/get/{teamMemberId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "teamMemberId")Integer teamMemberId){
		return cisSwTeamMemberService.doSelect(teamMemberId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisSwTeamMember
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwTeamMember cisSwTeamMember){
		return cisSwTeamMemberService.doSave(cisSwTeamMember);
	}
	
	/**
	 * 删除
	 * 
	 * @param teamMemberId
	 * @return
	 */
	@RequestMapping(value = "/del/{teamMemberId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "teamMemberId")Integer teamMemberId){
		return cisSwTeamMemberService.doDelete(teamMemberId);
	}

}
