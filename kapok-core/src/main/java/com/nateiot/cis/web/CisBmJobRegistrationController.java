package com.nateiot.cis.web;

import com.nateiot.cis.domain.CisBmJobRegistration;
import com.nateiot.cis.service.CisBmJobRegistrationService;
import com.nateiot.core.common.web.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 求职登记
 * 
 * @author xiaguangjun
 *
 */
@Controller
@RequestMapping(value = "/jobRegistration")
public class CisBmJobRegistrationController {
	
	@Autowired
	private CisBmJobRegistrationService cisBmJobRegistrationService;
	
	/**
	 * 加载视图
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/applyJob/applyJob";
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

			return cisBmJobRegistrationService.doSearch(
					SearchUtil.getSpecification(CisBmJobRegistration.class, req),
					SearchUtil.getPageableWithOrderBy(req, "jobregId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmJobRegistration cisBmJobRegistration) {

		return cisBmJobRegistrationService.doSave(cisBmJobRegistration);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{jobregId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "jobregId") Integer jobregId){
		return cisBmJobRegistrationService.doSelect(jobregId);
	}
	

	/**
	 * 硬删除记录
	 * 
	 * @param jobregId
	 * @return
	 */
	@RequestMapping(value = "/del/{jobregId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "jobregId") Integer jobregId){
		return cisBmJobRegistrationService.doDelete(jobregId);
	}
	

}
