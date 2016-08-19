package com.nateiot.cis.web;

import com.nateiot.cis.domain.CisBmRecruitment;
import com.nateiot.cis.service.CisBmRecruitmentService;
import com.nateiot.core.common.web.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 单位招聘
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/recruitment")
public class CisBmRecruitmentController {
	
	@Autowired
	private CisBmRecruitmentService cisBmRecruitmentService;
	
	/**
	 * 加载视图
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/recruitment/recruitment";
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
			return cisBmRecruitmentService.doSearch(
					SearchUtil.getSpecification(CisBmRecruitment.class, req),
					SearchUtil.getPageableWithOrderBy(req, "recruitmentId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmRecruitment cisBmRecruitment) {
		return cisBmRecruitmentService.doSave(cisBmRecruitment);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{recruitmentId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "recruitmentId") Integer recruitmentId){
		return cisBmRecruitmentService.doSelect(recruitmentId);
	}
	

	/**
	 * 硬删除记录
	 * 
	 * @param recruitmentId
	 * @return
	 */
	@RequestMapping(value = "/del/{recruitmentId}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "recruitmentId") Integer recruitmentId){
		return cisBmRecruitmentService.doDelete(recruitmentId);
	}
	


}
